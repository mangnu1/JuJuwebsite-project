package com.test.ju.domain.dto.member;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.ju.domain.entity.Member;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MemberInsertDTO {
	
	private String email;
	private String name;
	private String pass;
	private String userIP;
	
	
	public MemberInsertDTO passEncode(PasswordEncoder pe) {
		this.pass=pe.encode(pass);
		return this;
	}
	
	
	public Member ToMember() {
		return Member.builder()
				.email(email).name(name).pass(pass).userIp(userIP)
				.build();
	}
	
	
	
}
