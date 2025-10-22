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
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.exemplo_spring_boot_2.dto.funcionario.FuncionarioDto;
import com.delegrego.exemplo_spring_boot_2.service.FuncionarioService;

import jakarta.validation.Valid;

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

	/**
	 * Endpoint para cadastrar um novo funcionário
	 * 
	 * @param funcionarioDto - Objeto Funcionario a ser cadastrado
	 * @return ResponseEntity com status CREATED
	 */
	@PostMapping
	public ResponseEntity<Void> cadastrarFuncionario(@Valid @RequestBody FuncionarioDto funcionarioDto) {
		servico.cadastrarFuncionario(funcionarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Endpoint para listar todos os funcionários
	 * 
	 * @return ResponseEntity com a lista de funcionários
	 */
	@GetMapping
	public ResponseEntity<List<FuncionarioDto>> listarFuncionarios() {
		return ResponseEntity.status(HttpStatus.OK).body(servico.listarFuncionarios());
	}

	/**
	 * Endpoint para obter um funcionário pelo ID
	 * 
	 * @param id - ID do funcionário a ser obtido
	 * @return ResponseEntity com o funcionário encontrado
	 */
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDto> obterFuncionarioPorId(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.obterFuncionarioPorId(id));
	}

	/**
	 * Endpoint para atualizar um funcionário pelo ID
	 * 
	 * @param id - ID do funcionário a ser atualizado
	 * @param funcionarioDto - Objeto Funcionario com os dados atualizados
	 * @return ResponseEntity com status apropriado
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarFuncionario(@PathVariable int id,
			@Valid @RequestBody FuncionarioDto funcionarioDto) {
		servico.atualizarFuncionario(id, funcionarioDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	/**
	 * Endpoint para deletar um funcionário pelo ID
	 * 
	 * @param id - ID do funcionário a ser deletado
	 * @return ResponseEntity com status apropriado
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarFuncionario(@PathVariable int id) {
		servico.deletarFuncionario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
