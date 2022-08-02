package com.test.ju.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class NoticeFile {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nfno;
//	@Column(nullable = false)
//	private String link;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String orgName;
	@Column(nullable = false)
	private String newName;
	@Column(nullable = false)
	private boolean isDefImg;
	@Column(nullable = false)
	private long size;
	
	
	
	
}
