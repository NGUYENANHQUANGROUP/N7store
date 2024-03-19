package com.datn.service;

import java.util.List;

import com.datn.entity.Role;

public interface RoleService {

	List<Role> findAll();

	Role findById(String string);

}
