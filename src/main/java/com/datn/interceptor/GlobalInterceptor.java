package com.datn.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datn.dao.ProductBrandDAO;
import com.datn.service.ProductTypeService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	ProductTypeService productTypeService;
	
	@Autowired
	ProductBrandDAO brandDAO;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("TYPES", productTypeService.findAll());
		request.setAttribute("BRANDS", brandDAO.findAll());
	}
}
