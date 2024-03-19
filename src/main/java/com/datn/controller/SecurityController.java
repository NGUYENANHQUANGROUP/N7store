package com.datn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datn.entity.Account;
import com.datn.entity.Authority;
import com.datn.entity.Role;
import com.datn.service.AccountService;
import com.datn.service.AuthorityService;
import com.datn.service.RoleService;
import com.datn.service.TwilioOtpService;
import com.twilio.type.PhoneNumber;


@Controller
public class SecurityController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TwilioOtpService otpService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AuthorityService authorityService;
	
	@RequestMapping("/login")
	public String loginForm() {
		return "security/checkPhone";
	}
	
	@RequestMapping("/login/checkPhone")
	public String checkPhone(Model model, @RequestParam(value="phoneNumber") String phoneNumber) {
		try {
			Account acc = accountService.findById(phoneNumber);
			PhoneNumber to = new PhoneNumber("+84"+(phoneNumber.startsWith("0") ? phoneNumber.substring(1) : phoneNumber));
			String otp = otpService.generateOtp();
			acc.setPassword(otp);
			accountService.update(acc);
			otpService.sendOtp(to,otp);
		} catch (Exception e) {
			Account acc = new Account();
			acc.setUsername(phoneNumber);
			String otp = otpService.generateOtp();
			acc.setPassword(otp);
			acc.setActivated(true);
			Role role = roleService.findById("CUST"); // lấy role có id CUST
			Authority authority = new Authority();
			authority.setAccount(acc);
			authority.setRole(role);
			accountService.create(acc);
			authorityService.create(authority); 
			PhoneNumber to = new PhoneNumber("+84"+(phoneNumber.startsWith("0") ? phoneNumber.substring(1) : phoneNumber));
			otpService.sendOtp(to,otp);
		}
		
		
		model.addAttribute("PHONENUMBER", phoneNumber);
		return "security/Verification";
	}
	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "security/login";
	}
	
	@RequestMapping("/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "security/login";
	}
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
	@RequestMapping("/register")
	public String registerForm(Model model) {
		return "security/register";
	}
}
