package com.datn.dao;





import org.springframework.data.jpa.repository.JpaRepository;


import com.datn.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{
	
}
