package com.test.ju.domain.dto.customer;

import com.test.ju.domain.entity.Division;
import com.test.ju.domain.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class FaqListDTO {

	private long faqNo;
	
	private String title;//질문
	private String content; //응답E	
	
	private Division division;
	
	public FaqListDTO(NoticeEntity e){
		faqNo=e.getNoticeNo();
		title=e.getTitle();
		content=e.getContent();
		division=e.getDivision();
	}
	
	
}
