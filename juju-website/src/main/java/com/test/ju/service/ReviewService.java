package com.test.ju.service;

import org.springframework.ui.Model;

import com.test.ju.domain.dto.review.ReviewInsertDTO;
import com.test.ju.domain.dto.review.ReviewUpdateDTO;

public interface ReviewService {

	void save(ReviewInsertDTO dto);
	
	void list(Model model, int pageNo);

	void detail(long no, Model model);

	void update(long no, ReviewUpdateDTO dto);

	void delete(long no);


}
