package com.delegrego.exemplo_spring_boot_2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.dto.departamento.DepartamentoDto;
import com.delegrego.exemplo_spring_boot_2.service.DepartamentoService;

import jakarta.validation.Valid;

@RestController

// Define o endpoint base para todos os métodos deste controlador
// Todas as rotas deste controller começam com /departamentos 
@RequestMapping("/departamentos")
@CrossOrigin
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
	 */
	@PostMapping
	public void cadastrarDepartamento(@Valid @RequestBody DepartamentoDto departamentoDto) {
		servico.cadastrarDepartamento(departamentoDto);
	}

	/**
	 * Lista todos os departamentos
	 * 
	 * @return Lista de departamentos
	 */
	@GetMapping
	public List<DepartamentoDto> listarDepartamentos() {
		return servico.listarDepartamentos();
	}

	/**
	 * Obtém um departamento pelo ID
	 * 
	 * @param id - ID do departamento
	 * @return Departamento encontrado ou vazio se não existir
	 */
	@GetMapping("/{id}")
	public DepartamentoDto obterDepartamentoPorId(@PathVariable int id) {
		return servico.obterDepartamentoPorId(id);
	}

	/**
	 * Atualiza um departamento existente
	 * 
	 * @param departamentoDto - Departamento com os dados atualizados
	 */
	@PutMapping
	public void atualizarDepartamento(@Valid @RequestBody DepartamentoDto departamentoDto) {
		servico.atualizarDepartamento(departamentoDto);
	}

	/**
	 * Deleta um departamento pelo ID
	 * 
	 * @param id - ID do departamento a ser deletado
	 * @return ResponseEntity com status apropriado
	 */
	@DeleteMapping("/{id}")
	public void deletarDepartamento(@PathVariable int id) {
		servico.deletarDepartamento(id);
	}

}
