package com.test.ju.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.test.ju.domain.dto.review.ReviewDetailDTO;
import com.test.ju.domain.dto.review.ReviewInsertDTO;
import com.test.ju.domain.dto.review.ReviewListDTO;
import com.test.ju.domain.dto.review.ReviewUpdateDTO;
import com.test.ju.domain.repository.ReviewRepository;
import com.test.ju.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewServiceProc implements ReviewService {

	private final ReviewRepository repository;
	
	//리뷰 글쓰기 저장
	@Override
	public void save(ReviewInsertDTO dto) {
		
		repository.save(dto.toEntity());
		
		
	}
	
	//리뷰 리스트 보여주기
	@Override
	public void list(Model model, int pageNo) {
		
		//repository.findAll().forEach(System.out::println);
		List<ReviewListDTO> result = repository.findAllByOrderByCreatedDateDesc()
				.stream().map(ReviewListDTO::new).collect(Collectors.toList());
		model.addAttribute("list",result);
		
	}
	
	@Override
	public void detail(long no, Model model) {
		System.out.println(">>>>>>디테일 쿼리실행");
		model.addAttribute("detail",repository.findById(no).map(ReviewDetailDTO::new).get());
	}

	//리뷰 수정하기
	@Transactional//수정할때 서버 끝날때까지 커밋하지 않는 다시 돌아가감//수정할때 필요한 트랜잭션!//연결유지!
	@Override
	public void update(long no, ReviewUpdateDTO dto) {
		repository.findById(no).map(e->e.update(dto));
		
	}

	//리뷰 삭제
	@Override
	public void delete(long no) {
		repository.deleteById(no);
		//return "redirect:/reviews/list";
	}


}
