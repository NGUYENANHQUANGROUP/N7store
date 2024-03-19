package com.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart")
	public String view() {
		return "cart/cart";
	}
}
