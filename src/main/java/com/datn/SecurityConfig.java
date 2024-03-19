package com.datn;

import java.util.NoSuchElementException;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datn.entity.Account;
import com.datn.service.AccountService;

@Configuration
@EnableWebSecurity // cần thiết
public class SecurityConfig extends WebSecurityConfigurerAdapter { // file cấu hình (phải có mã framework security trong
																	// pom.xml)
	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder pe;

	// cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			System.out.println("ten dang nhap la:"+username);
			try {
				Account user = accountService.findById(username);
				String password = pe.encode(user.getPassword());
				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found!");
			}
		});
	}

	// phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// phân quyền sử dụng
		http.authorizeHttpRequests().antMatchers("/order/**").authenticated() // trang /order/** phải đăng nhập
				.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE") // trang /admin/** thì phải STAF Hoặc DIRE mới vào
																		// được
				.antMatchers("/rest/authorities").hasRole("DIRE") // trang phân quyền thì chỉ có DIRE mới được vào
				.anyRequest().permitAll(); // các trang còn lại thì cho phép vào không cần đăng nhập

		// Giao diện đăng nhập tự tạo
		http.formLogin().loginPage("/login") // địa chỉ request dẫn đến form đăng nhập
				.loginProcessingUrl("/login/check") // địa chỉ POST mà form login submit đến (action)
				.defaultSuccessUrl("/home", false) // khi đăng nhập thành công sẽ chuyển đến địa chỉ này hoặc false:
													// không
				.failureUrl("/login/error") // nếu lỗi thì chuyển sang error
				.usernameParameter("username") // tên của thẻ input bên html
				.passwordParameter("password"); // tên của thẻ input passowrd bên html

		http.rememberMe().tokenValiditySeconds(86400) // lưu 86400 giây
				.rememberMeParameter("remember"); // tên của thẻ input checkbox bên html (mặc định nếu ko đặt là
													// remember-me)

		http.exceptionHandling() // nếu đăng nhập rồi nhưng cố tình truy xuất đến các địa chỉ chưa được cấp quyền
				.accessDeniedPage("/security/unauthoried"); // chuyển đến địa chỉ unauthoried

		http.logout().logoutUrl("/logoff").logoutSuccessUrl("/home");
	}

	/* Cơ Chế Mã Hóa Mật Khẩu */
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* Cho phép truy xuất REST API từ bên ngoài (domain khác) */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
