package com.test.ju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/*")
@Controller
public class AdminController {
	
	//관리자 페이지 홈
	@GetMapping("home")
	public String adminHome() {
		return "admin/visual/list";
	}
	
	
//	//상품 아이템 등록하러 가기
//	@GetMapping("items/write")
//	public String write() {
//		return "admin/items/write";
//	}
	
}
