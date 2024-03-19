package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.datn.entity.Account;
import com.datn.entity.Order;
import com.datn.service.AccountService;
import com.datn.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public List<Order> getAll(){
		return orderService.findAll();
	}
	
	@PostMapping()
	public Order create(@RequestBody JsonNode order) {
		System.out.println("kiem tra :"+order);
		String username = order.get("account").get("username").asText();
		String fullname = order.get("fullname").asText();
		Account acc = accountService.findById(username);
		acc.setFullname(fullname);
		accountService.update(acc);
		 ((ObjectNode) order).remove("fullname");
		 System.out.println("hóa đơn:"+order);
		return orderService.create(order);
	}
	
	
	
	
}
