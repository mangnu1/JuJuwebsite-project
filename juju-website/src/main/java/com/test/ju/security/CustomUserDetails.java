package com.test.ju.security;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.test.ju.domain.entity.Member;

import lombok.Getter;


	@Getter
	//Super 클래스에 default 생성자(super())가 존재하지 않고 다른 생성자(오버로딩생성자)만 존재하는 경우 생성자 명시 
	public class CustomUserDetails extends User implements OAuth2User{
		//로그인하려 하는 작업
		
		private String email;
		private String name;
		private Map<String, Object> attributes;//이건 모지?
		
		
//		public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//			super(username, password, authorities);
		public CustomUserDetails(Member m) {
			super(m.getEmail(), m.getPass(), m.getRoleSet().stream()
															//.map(role->{return new SimpleGrantedAuthority(role.roleName);})//final필드이기 때문에 생성된 생성자 값이 있다?!
															.map(role->new SimpleGrantedAuthority(role.roleName))//final필드이기 때문에 생성된 생성자 값이 있다?!
															.collect(Collectors.toSet()));
				// Set<MemberRole> -> Collection<? extends GrantedAuthority> authorities
			// TODO Auto-generated constructor stub
			//default super생성자 있긴 하지만 그건 적용이 안돼서 super구현해줘야함 
		email=m.getEmail();
		name=m.getName();
			
		}


			
}

