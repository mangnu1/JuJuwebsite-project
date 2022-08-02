package com.test.ju.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.ReplyEntity;
import com.test.ju.domain.entity.Review;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>{

	List<ReplyEntity> findAllByReviewNoOrderByReplyNoDesc(long bno);

	
}
