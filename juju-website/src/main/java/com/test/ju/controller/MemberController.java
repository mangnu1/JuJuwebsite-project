package com.test.ju.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.ju.domain.dto.member.MemberInsertDTO;
import com.test.ju.service.MailService;
import com.test.ju.service.MeberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
		
	private final MeberService service;
	private final MailService mailService;
	
	
	@PostMapping("/common/signup")				//IP가져오기 위해 request받아오기
	public String signup(MemberInsertDTO dto, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		
		service.save(dto,  request, model);
		redirectAttributes.addFlashAttribute("success",dto.getName() + "님 회원가입을 축하합니다. 로그인 후 이용하세요!");
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/request-key/mail")
	public long requestMailKey(String email) {
		System.out.println(email);
		return mailService.mailSend(email);
	}
	
	@ResponseBody
	@GetMapping("/request-key/getKey")
	public String requestMailKey(HttpSession session) {
		
		System.out.println("마지막 접속시간 :"+session.getLastAccessedTime());
		System.out.println("생성시간 :"+session.getCreationTime());
		System.out.println("유지시간 :" +session.getMaxInactiveInterval());
		//System.out.println(email);
		return (String) session.getAttribute("mailKey");
	}
	
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession session) {
		session.removeAttribute("mailKey");
	}
	
}
