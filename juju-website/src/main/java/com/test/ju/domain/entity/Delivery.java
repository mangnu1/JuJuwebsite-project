package com.test.ju.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deliveryNo;
	
	@Column(nullable = false)
	private int zipcode;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String detail;
	@Column(nullable = false)
	private String status;
	
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY )
	private Orders orders;
	
	
	
}
