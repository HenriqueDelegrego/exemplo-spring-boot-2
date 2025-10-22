package com.delegrego.exemplo_spring_boot_2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para tratamento global de exceções na aplicação.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Tratamento para RuntimeException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 400 e mensagem da exceção
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> tratarRuntimeException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	/**
	 * Tratamento para EmailDuplicadoException.
	 * 
	 * @param ex - exceção lançada
	 * @return ResponseEntity com status 400 e mensagem da exceção
	 */
	@ExceptionHandler(EmailDuplicadoException.class)
	public ResponseEntity<String> tratarEmailDuplicadoException(EmailDuplicadoException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

}