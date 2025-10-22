package com.delegrego.exemplo_spring_boot_2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.dto.departamento.DepartamentoDto;
import com.delegrego.exemplo_spring_boot_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_spring_boot_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

@Service
public class DepartamentoService {

	// Maneira correta de acoplar camada de repositório
	private final DepartamentoRepository repo;

	private final FuncionarioRepository funcionarioRepo;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@Service, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public DepartamentoService(DepartamentoRepository repo, FuncionarioRepository funcionarioRepo) {
		this.repo = repo;
		this.funcionarioRepo = funcionarioRepo;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void cadastrarDepartamento(DepartamentoDto departamentoDTO) {

		DepartamentoEntity departamentoEntity = new DepartamentoEntity();

		departamentoEntity.setNmDepartamento(departamentoDTO.getNmDepartamento());

		repo.save(departamentoEntity);
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<DepartamentoDto> listarDepartamentos() {
		List<DepartamentoEntity> listaDepartamentoEntity = repo.findAll();

		List<DepartamentoDto> listaDepartamentoDto = new ArrayList<DepartamentoDto>();

		for (DepartamentoEntity d : listaDepartamentoEntity) {
			DepartamentoDto departamentoDto = new DepartamentoDto();
			departamentoDto.setIdDepartamento(d.getIdDepartamento());
			departamentoDto.setNmDepartamento(d.getNmDepartamento());

			listaDepartamentoDto.add(departamentoDto);
		}

		return listaDepartamentoDto;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public DepartamentoDto obterDepartamentoPorId(int id) {

		DepartamentoEntity departamentoEntity = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

		DepartamentoDto departamentoDto = new DepartamentoDto();
		departamentoDto.setIdDepartamento(departamentoEntity.getIdDepartamento());
		departamentoDto.setNmDepartamento(departamentoEntity.getNmDepartamento());

		return departamentoDto;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void atualizarDepartamento(int id, DepartamentoDto departamentoDTO) {

		DepartamentoEntity departamentoEntity = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

		departamentoEntity.setNmDepartamento(departamentoDTO.getNmDepartamento());

		repo.save(departamentoEntity);
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void deletarDepartamento(int id) {

		repo.findById(id).orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

		if (funcionarioRepo.existsByDepartamentoIdDepartamento(id)) {
			throw new RuntimeException("Não pode excluir departamentos com funcionários");
		}

		repo.deleteById(id);
	}

}
