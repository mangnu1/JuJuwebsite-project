package com.test.ju.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.ItemsFile;

@Repository
public interface ItemsFileRepository extends JpaRepository<ItemsFile, Long>{

}
