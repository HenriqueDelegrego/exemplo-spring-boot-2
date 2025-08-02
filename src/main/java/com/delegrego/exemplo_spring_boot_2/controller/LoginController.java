package com.delegrego.exemplo_spring_boot_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage() {
		return "login"; // Should resolve to login.html
	}
}