package com.datn.service;


import java.util.List;

import com.datn.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {
	public Order create(JsonNode orderData) ;
	
	public Order findById(Long id) ;
	
	
	public Order updateSTT(Order order);

	public List<Order> findAll();
}
