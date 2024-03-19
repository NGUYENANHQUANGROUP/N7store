package com.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.datn.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

	@Query("select od from OrderDetail od where od.order.id = ?1")
	List<OrderDetail> findAllDetailsById(Long id);
		
	
}