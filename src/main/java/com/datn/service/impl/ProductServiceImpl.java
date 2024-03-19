package com.datn.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.datn.service.ProductService;
import com.datn.dao.ProductDAO;
import com.datn.entity.Product;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO productDAO;



	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return productDAO.findById(id).get();
	}

	@Override
	public Page<Product> findByProductTypeId(Integer tId,Pageable pageable) {
		
		return productDAO.findByProductTypeId(tId,pageable);
	}

	@Override
	public Product create(Product product) {
	
		return productDAO.save(product);
	}

	@Override
	public Product update(Product product) {
	
		return productDAO.save(product);
	}

	@Override
	public void delete(Integer id) {
	
		productDAO.deleteById(id);
	}


	@Override
	public Page<Product> findByKeyWordsWithCategory(Integer tId, String string, Pageable pageable) {
	
		return productDAO.findByKeyWordsWithCategory( tId,  string,  pageable);
	}


	@Override
	public Page<Product> findByKeyWords(String string, Pageable pageable) {
		// TODO Auto-generated method stub
		return productDAO.findByKeyWords( string,  pageable);
	}


	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDAO.findAll();
	}

	@Override
	public Page<Product> getAll(Pageable pageable) {
		
		return productDAO.getAll(pageable);
	}

	@Override
	public Page<Product> productFilter(List<String> lscategories, List<String> lsbrands, Double min, Double max,
			Pageable pageable) {
		
		return productDAO.productFilter(lscategories, lsbrands, min, max,
				 pageable);
	}

}
