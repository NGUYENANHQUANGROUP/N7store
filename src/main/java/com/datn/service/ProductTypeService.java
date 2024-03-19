package com.datn.service;

import java.util.List;

import com.datn.entity.ProductType;

public interface ProductTypeService {

	List<ProductType> findAll();

	ProductType findById(Integer id);
	

}
