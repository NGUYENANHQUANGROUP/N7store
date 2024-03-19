package com.datn.service;

import java.util.List;

import com.datn.entity.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> getAllDetails(Long id);

	Double getAmountById(Long id);

}
