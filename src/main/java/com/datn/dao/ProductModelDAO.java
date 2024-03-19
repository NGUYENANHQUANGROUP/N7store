package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.datn.entity.ProductModel;

public interface ProductModelDAO extends JpaRepository<ProductModel, String>  {

}
