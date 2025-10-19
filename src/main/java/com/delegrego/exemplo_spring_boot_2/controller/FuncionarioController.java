package com.delegrego.exemplo_spring_boot_2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.dto.FuncionarioDto;
import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_spring_boot_2.service.FuncionarioService;

@RestController

// Define o endpoint base para todos os métodos deste controlador
// Todas as rotas deste controller começam com /funcionarios
@RequestMapping("/funcionarios")
@CrossOrigin
public class FuncionarioController {

	// Maneira correta de acoplar camada de serviço
	private final FuncionarioService servico;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@RestController, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public FuncionarioController(FuncionarioService servico) {
		this.servico = servico;
	}

	@PostMapping
	public void cadastrarFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
		System.out.println(funcionarioDto.getEndereco());
		servico.cadastrarFuncionario(funcionarioDto);
	}

	/**
	 * Endpoint para listar todos os funcionários
	 * 
	 * @return Lista de funcionários
	 */
	@GetMapping
	public List<FuncionarioDto> listarFuncionarios() {
		return servico.listarFuncionarios();
	}

	/**
	 * Endpoint para obter um funcionário por ID
	 * 
	 * @param id - ID do funcionário a ser obtido
	 * @return Funcionário encontrado ou vazio se não existir
	 */
	@GetMapping("/{id}")
	public Optional<FuncionarioEntity> obterFuncionarioPorId(@PathVariable int id) {
		return servico.obterFuncionarioPorId(id);
	}

	/**
	 * Endpoint para atualizar um funcionário
	 * 
	 * @param f - Funcionário com os dados atualizados
	 */
	@PutMapping
	public void atualizarFuncionario(@RequestBody FuncionarioEntity f) {
		servico.atualizarFuncionario(f);
	}

	/**
	 * Endpoint para deletar um funcionário por ID
	 * 
	 * @param id - ID do funcionário a ser deletado
	 */
	@DeleteMapping("/{id}")
	public void deletarFuncionario(@PathVariable int id) {
		servico.deletarFuncionario(id);
	}

}
