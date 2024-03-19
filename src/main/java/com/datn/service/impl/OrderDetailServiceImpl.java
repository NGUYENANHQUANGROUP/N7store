package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.dao.OrderDetailDAO;
import com.datn.entity.OrderDetail;
import com.datn.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired 
	OrderDetailDAO orderDetailDao;

	@Override
	public List<OrderDetail> getAllDetails(Long id) {
		
		return orderDetailDao.findAllDetailsById(id);
	}

	@Override
	public Double getAmountById(Long id) {
		// TODO Auto-generated method stub
		Double amount = 0.0;
		List<OrderDetail> lsOrderDetails = orderDetailDao.findAllDetailsById(id);
		for(OrderDetail od :lsOrderDetails) {
			amount+= od.getPrice()*od.getQuantity();
		}
		return amount;
	}

}
