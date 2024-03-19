package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.ProductType;

public interface ProductTypeDAO extends JpaRepository<ProductType, Integer>  {

}
