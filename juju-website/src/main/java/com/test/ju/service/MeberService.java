package com.test.ju.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.ju.domain.dto.member.MemberInsertDTO;

public interface MeberService {

	void save(MemberInsertDTO dto,  HttpServletRequest request, Model model);

}
