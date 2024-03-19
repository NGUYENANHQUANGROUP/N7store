package com.datn.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.service.ProductBrandService;
import com.datn.dao.ProductBrandDAO;
import com.datn.entity.ProductBrand;
@Service
public class ProductBrandServiceImpl implements ProductBrandService {
	@Autowired
	ProductBrandDAO productBrandDAO;

	@Override
	public List<ProductBrand> findAll() {
		// TODO Auto-generated method stub
		return productBrandDAO.findAll();
	}



}
