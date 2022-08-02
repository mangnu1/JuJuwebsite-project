package com.test.ju.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
