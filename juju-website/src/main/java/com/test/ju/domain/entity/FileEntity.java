package com.test.ju.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNo;
	@Column(nullable = false)
	private String link;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String orgName;
	@Column(nullable = false)
	private String newName;
	@Column(nullable = false)
	private long size;
	
	@JoinColumn(name = "no", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Review review;
	
	
	
}
