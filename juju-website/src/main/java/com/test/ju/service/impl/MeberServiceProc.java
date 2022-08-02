package com.test.ju.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.ju.domain.dto.member.MemberInsertDTO;
import com.test.ju.domain.repository.MemberRepository;
import com.test.ju.security.MemberRole;
import com.test.ju.service.MeberService;
import com.test.ju.utils.ClientIP;

@Service
public class MeberServiceProc implements MeberService {

	//DAO db에 접속하는 객체 :jpa Repository , mybatis-Mapper, jdbc-si-Connection(singleton으로 구성)
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Override
	public void save(MemberInsertDTO dto, HttpServletRequest request ,Model model) {
		dto.passEncode(pe);
		//ip취득 
		
		dto.setUserIP(request.getRemoteAddr());//ip취득. 내부 아이피만 가능
		dto.setUserIP(ClientIP.getClientIP(request));
		
		
		memberRepository.save(dto.ToMember().addRole(MemberRole.USER).addRole(MemberRole.ADMIN));
		
	}
	
}
