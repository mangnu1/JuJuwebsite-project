package com.test.ju.domain.dto.review;

import com.test.ju.domain.entity.Member;
import com.test.ju.domain.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewInsertDTO {
	
	private long no;
	private String title;
	private String content;
	private String writer;
	
	
	
	public Review toEntity() {
		return Review.builder()
				.title(title).content(content).writer(writer)
				//.member(Member.builder().memberNo(no).build())
				.build();
	}
	
	
}
