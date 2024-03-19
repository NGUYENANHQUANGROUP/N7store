package com.datn.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.entity.Product;
import com.datn.entity.ProductBrand;
import com.datn.entity.ProductType;
import com.datn.service.ProductBrandService;
import com.datn.service.ProductService;
import com.datn.service.ProductTypeService;
import com.datn.service.SessionService;

@Controller
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductTypeService 	productTypeService;
	
	@Autowired
	ProductBrandService productBrandService;

	@Autowired
	SessionService session;
	
	//load Brands
	
/*	@GetMapping("list")
	public String list(Model model, @RequestParam("tId") Optional<Integer> tId,
	        @RequestParam(value = "keyword") Optional<String> kw,
	        @RequestParam(value = "field", required = false) String field,
	        @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

	    Sort.Direction sortDirection = Sort.Direction.DESC; // Sắp xếp list sp theo hướng giảm dần (mặc định)
	    if (field != null && field.contains("ASC")) {
	        sortDirection = Sort.Direction.ASC; // Sắp xếp theo hướng tăng dần nếu giá trị tham số là "asc"
	    }
	    String sortField = "product_price"; // Sắp xếp theo trường "price" nếu không có trường được chỉ định

	    Pageable pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 24, Sort.by(sortDirection, sortField));
	    Page<Product> page;

	    if (tId.isPresent()) {
	        if (!kw.isPresent() || kw.get().isEmpty()) {
	        	
	        	page = productService.findByProductTypeId(tId.get(), pageable);
	        } else {
	            page = productService.findByKeyWordsWithCategory(tId.get(), "%" + kw.get() + "%", pageable);
	        }

	    } else {
	        if (!kw.isPresent() || kw.get().isEmpty()) {
	            
	        	page = productService.getAll(pageable);
	            
	        } else {
	            page = productService.findByKeyWords("%" + kw.get() + "%", pageable);
	        }
	    }

	    model.addAttribute("PRODUCTS", page);

	    return "product/list";
	}*/

	@GetMapping("list")
	public String list(Model model, @RequestParam("typeId") Optional<Integer> typeId,
	        @RequestParam(value = "keyword") Optional<String> keyword,
	        @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

		Integer tId = typeId.orElse(session.get("category", 0));
		session.set("category", tId);

		String kwords = keyword.orElse(session.get("keyword", ""));
		session.set("keyword", kwords);
		
	    Pageable pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 24);
	    Page<Product> page;
	    
	    
	    if (tId != null && tId>0) {
	        if (kwords == null || kwords.trim().equals("")) {
	            page = productService.findByProductTypeId(tId, pageable);
	        } else {
	            page = productService.findByKeyWordsWithCategory(tId, "%" + kwords + "%", pageable);
	        }
	    } else {
	        if (kwords == null || kwords.trim().equals("")) {
	            page = productService.getAll(pageable);
	        } else {
	            page = productService.findByKeyWords("%" + kwords + "%", pageable);
	        }
	    }

	    model.addAttribute("PRODUCTS", page);
	    model.addAttribute("keyword", kwords);
	    return "product/list";
	}
	
	@GetMapping("filter")
	public String productFilter(@RequestParam(value = "categories") Optional<List<String>> categories,
			@RequestParam(value = "brands") Optional<List<String>> brands,
			@RequestParam(value = "minPrice") Optional<Double> minPrice,
			@RequestParam(value = "maxPrice") Optional<Double> maxPrice,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,Model model) {
		
		List<String> defaultcategories = productTypeService.findAll().stream()
			    .map(ProductType::getType_name)
			    .collect(Collectors.toList());
		List<String> lscategories = categories.orElse(defaultcategories);
		
		
		List<String> defaultbrands = productBrandService.findAll().stream()
			    .map(ProductBrand::getBrand_name)
			    .collect(Collectors.toList()); 
		List<String> lsbrands = brands.orElse(defaultbrands);
		
		Double min = minPrice.orElse(session.get("minPrice", 1000000.0));
		session.set("minPrice", min);

		Double max = maxPrice.orElse(session.get("maxPrice", 10000000.0));
		session.set("maxPrice", max);
		
		Pageable pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 24);
		
		Page<Product> page = productService.productFilter(lscategories, lsbrands, min, max, pageable);
		
		model.addAttribute("PRODUCTS", page);
		
		return "product/list";
		
	}
	
	@GetMapping("detail/{id}")
	public String detail(Model model, @PathVariable int id) {
		Product p = productService.findById(id);
		
		List<Product> list = p.getProductModel().getProducts();// lấy các sản phẩm tương tự
		model.addAttribute("PRODUCTS", list);
		model.addAttribute("PRODUCT", p);
		return "product/detail";
	}
	
	
	

}
