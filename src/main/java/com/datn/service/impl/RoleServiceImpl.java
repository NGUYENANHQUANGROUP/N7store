package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.dao.RoleDAO;
import com.datn.entity.Role;
import com.datn.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO roleDao;
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
	@Override
	public Role findById(String string) {
		// TODO Auto-generated method stub
		return roleDao.findById(string).get();
	}

}
