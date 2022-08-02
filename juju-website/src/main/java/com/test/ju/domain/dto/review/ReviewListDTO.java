package com.test.ju.domain.dto.review;

import java.time.LocalDateTime;

import com.test.ju.domain.entity.Review;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ReviewListDTO {
	
	private long no;
	
	private String title;
	
	private String content;

	private String writer;
	
	private int ReadCount;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;
	
	
	public ReviewListDTO(Review r) {
		this.no=r.getNo();
		this.title=r.getTitle();
		this.content=r.getContent();
		this.writer=r.getWriter();
		this.ReadCount=r.getReadCount();
		this.createdDate=r.getCreatedDate();
		this.updatedDate=r.getUpdatedDate();
		
	}
	
	
	
}
