package com.test.ju.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ItemsCategory {
	 
	@Id//임의로 숫자 지정할거기 때문에엥 안써줌 
	private long caNo;
	
	public ItemsCategory createNo() {
		caNo=cateA.code +code;
		return this;
	}
	
	private String name;
	private long code;
	
	@Enumerated(EnumType.STRING)
	private CategoryA cateA;//상위 카테고리
	
	@Builder.Default
	@ManyToMany(mappedBy = "categorys")
	Set<ItemsEntity> items=new HashSet<>();
	
	
	
}
