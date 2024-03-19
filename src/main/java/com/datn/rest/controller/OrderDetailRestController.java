package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.entity.OrderDetail;
import com.datn.service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderDetails")
public class OrderDetailRestController {
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("{id}")
	public List<OrderDetail> getAll(@PathVariable("id") Long id){
		return orderDetailService.getAllDetails(id);
	}
}
