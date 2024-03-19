package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.datn.service.ProductTypeService;
import com.datn.dao.ProductTypeDAO;

import com.datn.entity.ProductType;
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	ProductTypeDAO productTypeDao;
	@Override
	public List<ProductType> findAll() {
		
		return productTypeDao.findAll();
	}
	@Override
	public ProductType findById(Integer id) {
		// TODO Auto-generated method stub
		return productTypeDao.findById(id).get();
	}


}
