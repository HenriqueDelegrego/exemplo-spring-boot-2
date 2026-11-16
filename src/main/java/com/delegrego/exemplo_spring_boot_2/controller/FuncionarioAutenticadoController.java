package com.delegrego.exemplo_spring_boot_2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.security.FuncionarioAutenticadoResponse;

/**
 * Controller para gerenciar informações do funcionário autenticado
 */

@RequestMapping("/funcionarios/me")
@CrossOrigin
@RestController
public class FuncionarioAutenticadoController {

	@GetMapping
	public ResponseEntity<FuncionarioAutenticadoResponse> me(@AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(new FuncionarioAutenticadoResponse(jwt.getSubject(), jwt.getClaimAsString("name"),
				jwt.getClaimAsString("roles")));
	}
}
