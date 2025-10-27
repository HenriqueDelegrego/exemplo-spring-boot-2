package com.delegrego.exemplo_spring_boot_2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.delegrego.exemplo_spring_boot_2.dto.departamento.request.DepartamentoRequestDto;
import com.delegrego.exemplo_spring_boot_2.dto.departamento.response.DepartamentoResponseDto;
import com.delegrego.exemplo_spring_boot_2.service.DepartamentoService;

import jakarta.validation.Valid;

@RestController

// Define o endpoint base para todos os métodos deste controlador
// Todas as rotas deste controller começam com /departamentos 
@RequestMapping("/departamentos")
@CrossOrigin
// @RequiredArgsConstructor -> Pode ser utilizado em vez do construtor
public class DepartamentoController {

	// Maneira correta de acoplar camada de serviço
	private final DepartamentoService servico;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@RestController, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public DepartamentoController(DepartamentoService servico) {
		this.servico = servico;
	}

	/**
	 * Cadastra um novo departamento
	 * 
	 * @param departamentoDto - Objeto Departamento a ser cadastrado
	 * @return ResponseEntity com status CREATED
	 */
	@PostMapping
	public ResponseEntity<Void> cadastrarDepartamento(@Valid @RequestBody DepartamentoRequestDto departamentoDto) {
		servico.cadastrarDepartamento(departamentoDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Lista todos os departamentos
	 * 
	 * @return ResponseEntity com a lista de departamentos
	 */
	@GetMapping
	public ResponseEntity<List<DepartamentoResponseDto>> listarDepartamentos() {
		return ResponseEntity.status(HttpStatus.OK).body(servico.listarDepartamentos());
	}

	/**
	 * Obtém um departamento pelo ID
	 * 
	 * @param id - ID do departamento a ser obtido
	 * @return ResponseEntity com o departamento encontrado
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DepartamentoResponseDto> obterDepartamentoPorId(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.obterDepartamentoPorId(id));
	}
	
	
	/**
	 * Endpoint para pesquisar departamentos por nome
	 * 
	 * @param pesquisa - Termo de pesquisa
	 * @return ResponseEntity com a lista de departamentos encontrados
	 */
	@GetMapping("/search")
	public ResponseEntity<List<DepartamentoResponseDto>> pesquisarDepartamentos(@RequestParam String pesquisa) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.pesquisarDepartamentos(pesquisa));
	}

	/**
	 * Atualiza um departamento pelo ID
	 * 
	 * @param id              - ID do departamento a ser atualizado
	 * @param departamentoDto - Objeto Departamento com os dados atualizados
	 * @return ResponseEntity com status apropriado
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarDepartamento(@PathVariable int id,
			@Valid @RequestBody DepartamentoRequestDto departamentoDto) {
		servico.atualizarDepartamento(id, departamentoDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * Deleta um departamento pelo ID
	 * 
	 * @param id - ID do departamento a ser deletado
	 * @return ResponseEntity com status apropriado
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarDepartamento(@PathVariable int id) {
		servico.deletarDepartamento(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
