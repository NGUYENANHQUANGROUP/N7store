package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datn.entity.Account;
import com.datn.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public List<Account> getAdmin(@RequestParam("admin")Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	

}
