package com.test.ju.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.ju.domain.entity.Member;
import com.test.ju.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{

		//DAO : jpa-Repository
		private final MemberRepository repository;
		
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// username -> login페이지에서 입력한 email 정보입니다.
			System.out.println(username);
			//db에 회원존재하는지 체크 존재하면 UserDetails으로 세팅해서 리턴
			Optional<Member> result=repository.findByEmailAndIsDeletedAndIsSocial(username, false, false); 
			//isDeleted : true는 탈퇴회원, false가 정상회원
			//isSocial : false는 일반회원, true 소셜가입 회원
			
			if(result.isEmpty()) {
				throw new UsernameNotFoundException("존재하지 않거나 탈퇴회원");
			}
			//Member memberEntity=result.get();//entity객체로 가져옴
			//UserDetails 타입으로 리턴 User활용
			
			
			//return new User(memberEntity.getEmail(), memberEntity.getPass(), null);
			System.out.println(">>>>>>>Role read");//get메서드 실행되는 시점에서 role이 실행됨
			return new CustomUserDetails(result.get());
			
			
			//d이거 끝날대까지 트랜잭션 유지하든가 해야댐? 안그러면 롤이 안옴?
		}

	}
	

