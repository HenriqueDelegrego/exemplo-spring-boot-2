package com.delegrego.exemplo_spring_boot_2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.security.FuncionarioRole;

@RestController
@CrossOrigin
public class RoleController {

	@GetMapping("/api/roles")
	public ResponseEntity<FuncionarioRole[]> listarRoles() {
		return ResponseEntity.status(HttpStatus.OK).body(FuncionarioRole.values());
	}

}
