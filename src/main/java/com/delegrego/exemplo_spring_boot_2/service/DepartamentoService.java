package com.delegrego.exemplo_spring_boot_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.model.Departamento;
import com.delegrego.exemplo_spring_boot_2.repo.DepartamentoRepository;

@Service
public class DepartamentoService {

	// Maneira correta de acoplar camada de repositório
	private final DepartamentoRepository repo;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@Service, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public DepartamentoService(DepartamentoRepository repo) {
		this.repo = repo;
	}

	public void cadastrarDepartamento(Departamento d) {
		repo.save(d);
	}

	public List<Departamento> listarDepartamentos() {
		return repo.findAll();
	}

	public Optional<Departamento> obterDepartamentoPorId(int id) {
		return repo.findById(id);
	}

	public void atualizarDepartamento(Departamento d) {
		repo.save(d);
	}

	public void deletarDepartamento(int id) {
		repo.deleteById(id);
	}

}
