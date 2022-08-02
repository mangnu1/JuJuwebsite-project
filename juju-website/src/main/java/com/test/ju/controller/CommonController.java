package com.test.ju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	
	//회원 로그인 페이지 이동
	@GetMapping("/common/signin")
	public String signin() {
		return "member/signin";
	}
	
	@GetMapping("/signinPage")
	public String signinPage() {
		return "index";//리턴 계속 돈다는게 머선소리지? 성호님 머선 소리예요?
	}
	
	@GetMapping("/common/signup")
	public String signup() {
		return "member/signup";
	}
	
	@GetMapping("/common/ordercheck")
	public String ordercheck() {
		return "member/ordercheck";
	}
	
	@GetMapping("/common/cart")
	public String cart() {
		return "member/cart";
	}
	
	
	
	
}
