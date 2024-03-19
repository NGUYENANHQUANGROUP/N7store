package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.datn.dao.AccountDAO;
import com.datn.entity.Account;
import com.datn.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;

	@Override
	public Account findById(String username) {
		return accountDAO.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return accountDAO.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDAO.findAll();
	}

	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		return accountDAO.save(account);
	}

	@Override
	public Account update(Account acc) {
		// TODO Auto-generated method stub
		return accountDAO.save(acc);
	}


}
