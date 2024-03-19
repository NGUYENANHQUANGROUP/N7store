package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.OrderDetailGuest;

public interface OrderDetailGuestDAO extends JpaRepository<OrderDetailGuest, Long> {

}
