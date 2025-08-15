package com.delegrego.exemplo_spring_boot_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.model.Funcionario;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

@Service
public class FuncionarioService {

	// Maneira correta de acoplar camada de repositório
	private final FuncionarioRepository repo;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@Service, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public FuncionarioService(FuncionarioRepository repo) {
		this.repo = repo;
	}

	public void cadastrarFuncionario(Funcionario f) {
		if (repo.findByCpf(f.getCpf()).isPresent()) {
			throw new RuntimeException("Já existe um funcionário com esse cpf");
		}

		if (repo.findByEmail(f.getEmail()).isPresent()) {
			throw new RuntimeException("Já existe um funcionário com esse email");
		}

		repo.save(f);
	}

	public List<Funcionario> listarFuncionarios() {
		return repo.findAll();
	}

	public Optional<Funcionario> obterFuncionarioPorId(int id) {
		return repo.findById(id);
	}

	public void atualizarFuncionario(Funcionario f) {
		repo.save(f);
	}

	public void deletarFuncionario(int id) {
		repo.deleteById(id);
	}

}
