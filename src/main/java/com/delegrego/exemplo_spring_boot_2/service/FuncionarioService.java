package com.delegrego.exemplo_spring_boot_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('GERENTE')")
	public void cadastrarFuncionario(Funcionario f) {

		validarEmailUnico(f.getEmail(), f.getIdFuncionario());

		validarCpfUnico(f.getCpf(), f.getIdFuncionario());

		repo.save(f);
	}

    @PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<Funcionario> listarFuncionarios() {
		return repo.findAll();
	}

    @PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public Optional<Funcionario> obterFuncionarioPorId(int id) {
		return repo.findById(id);
	}

    @PreAuthorize("hasRole('GERENTE')")
	public void atualizarFuncionario(Funcionario f) {

		validarEmailUnico(f.getEmail(), f.getIdFuncionario());

		validarCpfUnico(f.getCpf(), f.getIdFuncionario());

		repo.save(f);
	}

    @PreAuthorize("hasRole('GERENTE')")
	public void deletarFuncionario(int id) {
		repo.deleteById(id);
	}

	private void validarCpfUnico(String cpf, Integer id) {
		boolean cpfEmUso;
		if (id == null) {
			cpfEmUso = repo.findByCpf(cpf).isPresent();
		} else {
			cpfEmUso = repo.existsByCpfAndIdFuncionarioNot(cpf, id);
		}

		if (cpfEmUso) {
			throw new RuntimeException("Já existe um funcionário com esse CPF");
		}
	}

	private void validarEmailUnico(String email, Integer id) {
		boolean emailEmUso;
		if (id == null) {
			emailEmUso = repo.findByEmail(email).isPresent();
		} else {
			emailEmUso = repo.existsByEmailAndIdFuncionarioNot(email, id);
		}

		if (emailEmUso) {
			throw new RuntimeException("Já existe um funcionário com esse email");
		}
	}

}
