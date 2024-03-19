package com.datn.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.service.ProductModelService;
import com.datn.dao.ProductModelDAO;
import com.datn.entity.ProductModel;
@Service
public class ProductModelServiceImpl implements ProductModelService {
	@Autowired
	ProductModelDAO productModelDAO;

	@Override
	public List<ProductModel> findAll() {
		// TODO Auto-generated method stub
		return productModelDAO.findAll();
	}



}
