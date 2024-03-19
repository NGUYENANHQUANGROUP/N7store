package com.datn.service.impl;

import java.util.List;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.datn.dao.OrderDAO;
import com.datn.dao.OrderDetailDAO;
import com.datn.entity.Order;
import com.datn.entity.OrderDetail;
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper(); // sử dụng objectmapper để chuyển json thành các đối tượng cần thiết
		
		Order order = mapper.convertValue(orderData, Order.class);  // chuyển orderData(json) thành order
		orderDAO.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type) // lấy orderdetails // dùng convertValue chuyển json thành list orderdetails
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailDAO.saveAll(details); // dùng saveAll() để lưu nhiều orderDetails 1 lúc
		
		return order; // trả về order vừa tạo ra trong CSDL
	}
	
	public Order findById(Long id) {
		return orderDAO.findById(id).get();
	}
	

	@Override
	public Order updateSTT(Order order) {
		return orderDAO.save(order);
	}

	@Override
	public List<Order> findAll() {
		
		return orderDAO.findAll();
	}
}
