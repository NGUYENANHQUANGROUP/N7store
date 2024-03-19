package com.datn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.datn.entity.Product;

public interface ProductService {
	Product findById(Integer id);
	Page<Product> getAll(Pageable pageable);
	Page<Product> findByProductTypeId(Integer tId,Pageable pageable);
	Product create(Product product);
	Product update(Product product);
	void delete(Integer id);
	Page<Product> findByKeyWordsWithCategory(Integer tId, String string, Pageable pageable);
	Page<Product> findByKeyWords(String string, Pageable pageable);
	List<Product> findAll();
	Page<Product> productFilter(List<String> lscategories, List<String> lsbrands, Double min, Double max,
			Pageable pageable);
}
