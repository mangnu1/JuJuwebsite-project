package com.test.ju.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.ItemsCategory;
import com.test.ju.domain.entity.ItemsEntity;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Long>{
	
	//조인쿼리?
	List<ItemsEntity> findAllByCategorysCaNo(long caNo);

	List<ItemsEntity> findAllByCategorysCaNoBetween(long caNo, long l);
	//return 정보
}
