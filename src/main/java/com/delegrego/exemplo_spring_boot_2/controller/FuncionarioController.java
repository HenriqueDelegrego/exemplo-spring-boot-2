package com.delegrego.exemplo_spring_boot_2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.dto.funcionario.request.FuncionarioAtualizarDto;
import com.delegrego.exemplo_spring_boot_2.dto.funcionario.request.FuncionarioCriarDto;
import com.delegrego.exemplo_spring_boot_2.dto.funcionario.response.FuncionarioResponseDto;
import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_spring_boot_2.security.FuncionarioAutenticadoResponse;
import com.delegrego.exemplo_spring_boot_2.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin
// @RequiredArgsConstructor -> Pode ser utilizado em vez do construtor
public class FuncionarioController {

	private final FuncionarioService servico;

	public FuncionarioController(FuncionarioService servico) {
		this.servico = servico;
	}

	@GetMapping("/me")
	public ResponseEntity<FuncionarioAutenticadoResponse> me(@AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(new FuncionarioAutenticadoResponse(jwt.getSubject(), jwt.getClaimAsString("name"),
				jwt.getClaimAsString("roles")));
	}

	@PostMapping
	public ResponseEntity<FuncionarioEntity> cadastrarFuncionario(
			@Valid @RequestBody FuncionarioCriarDto funcionarioDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(servico.cadastrarFuncionario(funcionarioDto));
	}

	/**
	 * Lista os funcionários cadastrados, permitindo filtrar os resultados por meio
	 * de um termo de pesquisa.
	 *
	 * @param pesquisa termo utilizado para filtrar os funcionários; opcional
	 * @return resposta HTTP com a lista de {@link FuncionarioResponseDto} e status
	 *         {@link HttpStatus#OK}
	 */
	@GetMapping
	public ResponseEntity<List<FuncionarioResponseDto>> listarFuncionarios(
			@RequestParam(required = false) String pesquisa) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.listarFuncionarios(pesquisa));
	}

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioResponseDto> obterFuncionarioPorId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.obterFuncionarioPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioEntity> atualizarFuncionario(@PathVariable Long id,
			@Valid @RequestBody FuncionarioAtualizarDto funcionarioDto) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.atualizarFuncionario(id, funcionarioDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
		servico.deletarFuncionario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
