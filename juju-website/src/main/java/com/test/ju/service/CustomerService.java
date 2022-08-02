package com.test.ju.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.ju.domain.dto.customer.NoticeInsertDTO;

public interface CustomerService {

	void list(Model model, int divno);
	
	void list(Model model, String div);

	ModelAndView list(ModelAndView mv, int divno);

	String save(int divno, NoticeInsertDTO dto);

	String tempFileupload(MultipartFile file);


}
