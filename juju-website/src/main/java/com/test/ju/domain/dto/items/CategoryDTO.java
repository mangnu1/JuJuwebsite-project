package com.test.ju.domain.dto.items;

import com.test.ju.domain.entity.CategoryA;
import com.test.ju.domain.entity.ItemsCategory;

import lombok.Getter;

@Getter
public class CategoryDTO {
	
	
	private long caNo;
	
	private String name;
	private long code;
	
	 CategoryA catea;
	
	public CategoryDTO(ItemsCategory e) {
		this.caNo = e.getCaNo();
		this.name = e.getName();
		this.code = e.getCode();
		catea=e.getCateA();
	}
	
	
	
	

}
