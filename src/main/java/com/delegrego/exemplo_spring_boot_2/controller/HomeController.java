package com.delegrego.exemplo_spring_boot_2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

	@GetMapping
	public String rootRedirect(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			return "redirect:/pages/html/lista-funcionarios.html";
		}
		return "redirect:/pages/html/login.html";
	}

}
