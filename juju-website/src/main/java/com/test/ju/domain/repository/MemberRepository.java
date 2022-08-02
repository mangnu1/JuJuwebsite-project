package com.test.ju.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByEmailAndIsDeletedAndIsSocial(String username, boolean isDeleted, boolean isSocail);
	
	//select resultType은 매핑 Entity로 설정 : Member //조회한 결과 리턴은 무조건 리절트타입
	Optional<Member> findByEmail(String email); //회원가입시점에서 이메일 중복 체크시에 사용

}
