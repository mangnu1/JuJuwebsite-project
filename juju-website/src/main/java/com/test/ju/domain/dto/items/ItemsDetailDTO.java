package com.test.ju.domain.dto.items;

import java.util.List;
import java.util.stream.Collectors;

import com.test.ju.domain.entity.ItemsEntity;

import lombok.Getter;

@Getter
public class ItemsDetailDTO {

	private long ino;
	private String name;
	private int price; //판매가
	private int sale; //할인가
	private int stock; //재고
	private String content;//리스트에는 굳이 내용 필요 x
	
	private List<ItemsFileDTO> files;//파일 띄우기 대표 이미지 띄우기

	public ItemsDetailDTO(ItemsEntity e) {
		this.ino = e.getIno();
		this.name = e.getName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
		this.content = e.getContent();
		
		this.files=e.getFiles().stream()
				.map(ItemsFileDTO::new)
				.collect(Collectors.toList());
		
	}
	
	
	
}
