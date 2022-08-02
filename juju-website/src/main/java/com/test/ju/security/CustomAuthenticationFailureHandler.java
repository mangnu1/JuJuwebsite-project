package com.test.ju.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
			
		
//		//비밀번호 여러번 생겼을때 사용
//		String msg="인증오류";
//		if(exception instanceof UsernameNotFoundException) {
//			msg="회원이 아니거나 탈퇴회원입니다.";
//		}else if(exception instanceof BadCredentialsException) {
//			msg="비밀번호 오류";
//		}
//		//정상 이메일인데 계속 비밀번호 틀리면 락걸어주는거?
		
		//Bean: class 위에 @component// 둘 중에 하나만 써라 config에 bean으로 하던가 component사용하든가
		
		setDefaultFailureUrl("/signin?error");//파라미터에 값은 안넣고 변수만 넣음?
		System.out.println(exception);
		super.onAuthenticationFailure(request, response, exception);
		
	}
}
