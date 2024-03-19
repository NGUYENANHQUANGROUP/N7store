package com.datn.dao;



import org.springframework.data.jpa.repository.JpaRepository;


import com.datn.entity.ProductBrand;

public interface ProductBrandDAO extends JpaRepository<ProductBrand, Integer> {

}
