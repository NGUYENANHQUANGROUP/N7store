package com.datn.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.datn.entity.Product;

import lombok.Data;

@Data
@Entity
@Table(name = "hot_products")
public class hotProduct {
	@Id
	private Integer product_id;
	private Integer quantity;
	@OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
	
}
