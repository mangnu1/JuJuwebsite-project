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
public class Cart {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartNo;
	@Column(nullable = false)
	private int orderPrice;
	@Column(nullable = false)
	private int count;
	
	@JoinColumn(name = "orderNo", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Orders orders;
	
//	@JoinColumn(name = "itemNo", nullable = false)
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Items item;
	
}
