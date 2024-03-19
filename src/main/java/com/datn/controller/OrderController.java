package com.datn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.datn.entity.Account;
import com.datn.entity.Order;
import com.datn.entity.OrderStatus;
import com.datn.service.AccountService;
import com.datn.service.OrderDetailService;
import com.datn.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/order/checkout")
	public String checkout(Model model) {
		
			Account acc = accountService.findById(request.getRemoteUser());
			model.addAttribute("ACCOUNT", acc);
			
			return "order/checkout";

		
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Account acc = accountService.findById(request.getRemoteUser());
		model.addAttribute("ACCOUNT", acc);
		model.addAttribute("ORDER_AMOUNT",orderDetailService.getAmountById(id));
		model.addAttribute("ORDER", orderService.findById(id));
		return "order/detail";
	}
	
	@RequestMapping("/order/list")
	public String detail(Model model) {
		Account acc = accountService.findById(request.getRemoteUser());
		model.addAttribute("ACCOUNT", acc);
		return "order/list";
	}
	
	@RequestMapping("/order/cancel/{id}")
	public String cancel(@PathVariable("id") Long id) {
		Order order = orderService.findById(id);
		//
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId("DH");
		//
		Order orderCancel = new Order();
		orderCancel.setId(order.getId());
		orderCancel.setCreateDate(order.getCreateDate());
		orderCancel.setPhonenumber(order.getPhonenumber());
		orderCancel.setAddress(order.getAddress());
		orderCancel.setOrderStatus(orderStatus);
		orderCancel.setAccount(accountService.findById(request.getRemoteUser()));
		orderService.updateSTT(orderCancel);
		return "redirect:/order/list";
	}
}
