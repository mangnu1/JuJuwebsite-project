package com.test.ju.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReplyEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long replyNo;
	
	@JoinColumn(name = "no", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Review review;
	
	
}
