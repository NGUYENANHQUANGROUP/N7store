package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.dto.hotProduct;

public interface StatsDAO extends JpaRepository<hotProduct, Integer> {
	
}
