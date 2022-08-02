package com.test.ju.domain.dto.items;

import com.test.ju.domain.entity.ItemsCategory;
import com.test.ju.domain.entity.ItemsEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ItemInsertDTO {

	private String name;
	private int price;
	private int sale;
	private boolean rate;//false:원, true:%
	private int stock;
	private String defImgName;
	private String addImgName;
	private String content;
	
	private long[] caNo;//pk //아이템 카테고리 추가 사용하기 위해 배열로 넣어준다
	
	public ItemsEntity toEntity() {
		if(rate) {
			sale=price*sale/100;
		}
		return ItemsEntity.builder()
				.name(name).price(price).sale(sale).stock(stock).content(content)
				.build();
	}
	
	
}
