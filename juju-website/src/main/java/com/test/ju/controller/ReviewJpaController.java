package com.test.ju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.ju.domain.dto.review.ReviewInsertDTO;
import com.test.ju.domain.dto.review.ReviewUpdateDTO;
import com.test.ju.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewJpaController {
	
	
	
	private final ReviewService service;
	
	
	//리뷰 작성 페이지 이동
	@GetMapping("/reviews/write")
		public String write() {
			return "view/review/write";
	}
		
	//리뷰 작성 입력한거 처리
	@PostMapping("/reviews/write")
	public String write(ReviewInsertDTO dto) {
		service.save(dto);
		return "redirect:/reviews/list";
}
	
	//리뷰목록으로 가기-화면에 list가 나와야함
	@GetMapping("/reviews/list") 
	public String listpage(Model model, @RequestParam(defaultValue = "1") int pageNo) {
		service.list(model, pageNo);
		
		return "view/review/list";
	}

	//리뷰목록에서 디테일로 넘어가기
	@GetMapping("/reviews/{no}")
	public String detailpage(@PathVariable long no, Model model) {
		service.detail(no, model);
		return "view/review/detail";
		}
	
	//리뷰 리스트 수정
	@PostMapping("/reviews/{no}")//putmapping 에서 바꾼건데 된다..뭐지
	public String update(@PathVariable long no, ReviewUpdateDTO dto) {
		service.update(no, dto);
		return "redirect:/reviews/"+no;
	}
	
	//리뷰 삭제
	@ResponseBody
	@DeleteMapping("/reviews/{no}")
	public void delete(@PathVariable long no) {
		service.delete(no);
	}
	
	
	
}
