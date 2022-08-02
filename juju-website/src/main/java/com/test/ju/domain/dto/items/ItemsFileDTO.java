package com.test.ju.domain.dto.items;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.test.ju.domain.entity.ItemsFile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemsFileDTO {

	private long ifno;
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg;
	private long size;
	
	public ItemsFileDTO(ItemsFile e) {
		super();
		this.ifno = e.getIfno();
		this.url = e.getUrl();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.isDefImg = e.isDefImg();
		this.size = e.getSize();
	}
	
	
	
	
}
