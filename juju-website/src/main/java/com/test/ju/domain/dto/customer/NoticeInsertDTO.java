package com.test.ju.domain.dto.customer;

import com.test.ju.domain.entity.Division;
import com.test.ju.domain.entity.NoticeEntity;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class NoticeInsertDTO {
	
	private long no;
	private String writer;
	private String title;
	private String content;
	private String defImgName;
	private String addImgName;
	
	private Division division;
	
	public NoticeEntity toEntity() {
		return NoticeEntity.builder()
				.writer(writer).title(title).content(content).division(division)
				.build();
	}
	
	
}
