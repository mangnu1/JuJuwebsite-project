package com.test.ju.domain.dto.items;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.test.ju.domain.entity.ItemsEntity;

import lombok.Getter;

@Getter
public class ItemsListDTO {
	
	private long ino;
	private String name;
	private int price;// 판매가 가격, 정가
	private int sale;//할인가 or 할인율
	private int stock;//재고
	
	private String defImgUrl;

	public ItemsListDTO(ItemsEntity e) {
		this.ino = e.getIno();
		this.name = e.getName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
	
		e.getFiles().forEach(fe->{
			if(fe.isDefImg())defImgUrl=fe.getUrl()+fe.getOrgName();//true이면 default이미지
		});
		
		//파일정보 : List<ItemsFile> -> List<ItemsFileDTO>//엔티티가 두개이기때문에 두번 해줘야함...?!!
//		this.files = e.getFiles().stream()
//				.map(ItemsFileDTO::new)
//				.collect(Collectors.toList());
	}
	
	
	
	
}
