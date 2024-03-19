package com.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VNPayController {
	@GetMapping("/callpayment")
	public String callPayment() {
		return "payment/callpayment";
	}
}
