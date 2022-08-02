package com.test.ju.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orders {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY )
	private long orderNo;
	
	@Column(nullable = false)
	private String status;
	
	@JoinColumn(name = "memberNo", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	@JoinColumn(name = "deliveryNo", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	private Delivery delivery;
	
	@Builder.Default
	@OneToMany(mappedBy = "orders")
	private List<Cart> carts=new Vector<Cart>();
	
	
}
