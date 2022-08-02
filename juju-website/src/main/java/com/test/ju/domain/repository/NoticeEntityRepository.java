package com.test.ju.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.Division;
import com.test.ju.domain.entity.NoticeEntity;

@Repository
public interface NoticeEntityRepository extends JpaRepository<NoticeEntity, Long>{
	
	List<NoticeEntity> findAllByDivision(Division division);
	
}
