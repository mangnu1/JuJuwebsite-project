package com.test.ju.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.ju.domain.dto.items.CategoryDTO;
import com.test.ju.domain.entity.CategoryA;
import com.test.ju.domain.entity.ItemsCategory;

public interface ItemsCategoryRepository extends JpaRepository<ItemsCategory, Long>{
	
	long countByCateA(CategoryA cateA);

	List<ItemsCategory> findByCaNoBetween(long caNo, long l);

	List<ItemsCategory> findByCateA(CategoryA catea);

}
