package com.datn.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

import com.datn.service.ProductService;



@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/home")
	public String home() {

		return "home/home";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "redirect:/admin/template/index.html";
	}
}
