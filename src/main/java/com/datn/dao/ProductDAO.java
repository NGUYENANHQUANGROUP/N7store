package com.datn.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.productModel.productType.id = ?1")
	Page<Product> findByProductTypeId(Integer tId, Pageable pageable);

	@Query("select p from Product p where p.productModel.productType.id = ?1 AND (p.product_name LIKE %?2% or p.product_desc LIKE %?2% or p.product_info LIKE %?2%)")
	Page<Product> findByKeyWordsWithCategory(Integer tId, String string, Pageable pageable);

	@Query("select p from Product p where p.product_name LIKE %?1% OR p.productModel.modelname LIKE %?1% OR p.productModel.productType.type_name LIKE %?1% OR p.productModel.productBrand.brand_name LIKE %?1% OR p.product_desc LIKE %?1%  OR p.product_info LIKE %?1%")
	Page<Product> findByKeyWords(String string, Pageable pageable);

	@Query("select p from Product p")
	Page<Product>getAll(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.productModel.productType.type_name IN ?1 AND p.productModel.productBrand.brand_name IN ?2 AND p.product_price BETWEEN ?3 AND ?4")
	Page<Product> productFilter(List<String> lscategories, List<String> lsbrands, Double min, Double max,
			Pageable pageable);
}
