package com.test.ju.domain.dto.review;

import lombok.Getter;
import lombok.Setter;


@Setter//업데이트 저장을 위해
@Getter
public class ReviewUpdateDTO {

	private String title;
	private String content;
	
}
