package com.delegrego.exemplo_spring_boot_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;
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

	@PreAuthorize("hasRole('GERENTE')")
	public void cadastrarFuncionario(FuncionarioEntity f) {

		if (repo.findByEmail(f.getEmail()).isPresent()) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		if (repo.findByCpf(f.getCpf()).isPresent()) {
			throw new RuntimeException("Usuário com esse CPF já existe");
		}

		repo.save(f);
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<FuncionarioEntity> listarFuncionarios() {
		return repo.findAll();
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public Optional<FuncionarioEntity> obterFuncionarioPorId(int id) {
		return repo.findById(id);
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void atualizarFuncionario(FuncionarioEntity f) {

		if (repo.existsByEmailAndIdFuncionarioNot(f.getEmail(), f.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		if (repo.existsByCpfAndIdFuncionarioNot(f.getCpf(), f.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse CPF já existe");
		}

		repo.save(f);
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void deletarFuncionario(int id) {
		repo.deleteById(id);
	}

}
