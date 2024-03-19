package com.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.entity.OrderGuest;

public interface OrderGuestDAO extends JpaRepository<OrderGuest, Long> {
	@Query("SELECT og FROM OrderGuest og WHERE og.phonenumber = ?1 AND og.address = ?2")
	List<OrderGuest> findByPhonenumberAndAddress(String phonenumber, String address);
}
