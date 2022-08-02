package com.test.ju.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
	CustomAuthenticationFailureHandler failureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	  
	  http 
	  	.authorizeHttpRequests((authz)-> 
	  		authz
	  		.antMatchers("/css/**", "/images/**", "/js/**","/favicon.ico*","/summernote/**", "/customer/**").permitAll()
	  		.antMatchers("/","/common/**","/view/**","/request-key/*").permitAll()//여기는 모두 올 수 이따
	  		.antMatchers("/admin/**").hasRole("ADMIN")
	  		.anyRequest().authenticated()//여기는 권한 허가 받아야 함! 
	  		);
	  
	  	//소셜 로그인
				http.oauth2Login(aouth2Login -> aouth2Login
					.loginPage("/common/signin")
					.userInfoEndpoint()
						.userService(customOAuth2UserService));
	  
	  
	  http 
	  		.formLogin(formLogin ->
  		formLogin 
	  		.loginPage("/common/signin")//getmapping
	  		.usernameParameter("email") //username
	  		.passwordParameter("pass")	//password
	  		.failureUrl("/common/signin?errMsg")
	  		.loginProcessingUrl("/common/signin")//폼 포스트 form action postmapping//controller 설정 안해줘도 시큐리티가 알아서 처리해줌 userdetailservice dapprovider가 해줌~
			.defaultSuccessUrl("/")
	  		.permitAll() 
	  		
	  				);
	  
	  http.csrf();
	  
	  return http.build();
	  
	  }
	  	
	  @Bean
	  WebSecurityCustomizer webSecurityCustomizer() {
	  	return (web) -> web.ignoring().antMatchers(
			  "/css/**" 
			  ,"/js/**"
			  ,"/images/**" 
			  ,"/favicon.ico"
			  
	  
	  ); 
	  
	}
	  
	  
	  
	 
	
	
}
