package com.test.ju.domain.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.dto.review.ReviewListDTO;
import com.test.ju.domain.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{




	Collection<Review> findAllByOrderByCreatedDateDesc();

	
}
