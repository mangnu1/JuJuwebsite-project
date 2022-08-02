package com.test.ju.domain.entity;


import java.util.Set;
import java.util.Vector;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.test.ju.security.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity{

	
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memberNo;

	@Column(unique= true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private  String name;
	
	@Column(nullable = false)
	private  String pass;
	
	@Column(nullable = false)
	private String userIp;
	
	private boolean isDeleted;
	
	private boolean isSocial;

	
//	@Builder.Default
//	@OneToMany(mappedBy = "member")
//	List<Review> reviews=new Vector<Review>();
	
	@OneToMany(mappedBy = "member")
	@Builder.Default
	List<Orders> orders=new Vector<Orders>();
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//관리적으로 문자열 쓰는게 안전하게 처리하기 좋음//DB저장 관리자
	@ElementCollection(fetch = FetchType.EAGER)//lazy가 default//Specifies a collection of instances of a basic type or embeddableclass
	private Set<MemberRole> roleSet=new HashSet<MemberRole>();
	
	
	public Member addRole(MemberRole role) {
		roleSet.add(role);
		return this;
	}
	
	public Member removeRole(MemberRole role) {
		roleSet.remove(role);
		return this;
	}
	
	public Member socialUpdate(String name, String pass) {
		this.name=name;
		this.pass=pass;
		return this;
	}
	
	
	
}