package com.datn.service;

import java.util.List;

import com.datn.entity.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> getAdministrators();

	List<Account> findAll();

	Account create(Account account);

	Account update(Account acc);

}