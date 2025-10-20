package com.delegrego.exemplo_spring_boot_2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.dto.departamento.DepartamentoFuncionarioDto;
import com.delegrego.exemplo_spring_boot_2.dto.endereco.EnderecoDto;
import com.delegrego.exemplo_spring_boot_2.dto.funcionario.FuncionarioDto;
import com.delegrego.exemplo_spring_boot_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_spring_boot_2.entity.EnderecoEntity;
import com.delegrego.exemplo_spring_boot_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_spring_boot_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

@Service
public class FuncionarioService {

	// Maneira correta de acoplar camada de repositório
	private final FuncionarioRepository repo;

	private final DepartamentoRepository departamentoRepo;

	// A anotação de @Autowired é opcional
	// O Spring já faz a injeção automaticamente se:
	// A classe é um bean (@Service, por exemplo)
	// E a classe tem somente 1 construtor
	@Autowired
	public FuncionarioService(FuncionarioRepository repo, DepartamentoRepository departamentoRepo) {
		this.repo = repo;
		this.departamentoRepo = departamentoRepo;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void cadastrarFuncionario(FuncionarioDto funcionarioDto) {

		if (repo.findByEmail(funcionarioDto.getEmail()).isPresent()) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		if (repo.findByCpf(funcionarioDto.getCpf()).isPresent()) {
			throw new RuntimeException("Usuário com esse CPF já existe");
		}

		FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

		DepartamentoEntity departamentoEntity = departamentoRepo
				.findById(funcionarioDto.getDepartamento().getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		FuncionarioEntity criadoPor = repo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		funcionarioEntity.setNome(funcionarioDto.getNome());
		funcionarioEntity.setCpf(funcionarioDto.getCpf());
		funcionarioEntity.setEmail(funcionarioDto.getEmail());
		funcionarioEntity.setSenha(funcionarioDto.getSenha());
		funcionarioEntity.setDataNascimento(funcionarioDto.getDataNascimento());
		funcionarioEntity.setSalario(funcionarioDto.getSalario());
		funcionarioEntity.setGerente(funcionarioDto.isGerente());

		funcionarioEntity.setEndereco(new EnderecoEntity());

		funcionarioEntity.getEndereco().setEstado(funcionarioDto.getEndereco().getEstado());
		funcionarioEntity.getEndereco().setCidade(funcionarioDto.getEndereco().getCidade());
		funcionarioEntity.getEndereco().setBairro(funcionarioDto.getEndereco().getBairro());
		funcionarioEntity.getEndereco().setLogradouro(funcionarioDto.getEndereco().getLogradouro());
		funcionarioEntity.getEndereco().setNumero(funcionarioDto.getEndereco().getNumero());
		funcionarioEntity.getEndereco().setCep(funcionarioDto.getEndereco().getCep());
		funcionarioEntity.setDepartamento(departamentoEntity);
		funcionarioEntity.setCriadoPor(criadoPor);

		repo.save(funcionarioEntity);
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public List<FuncionarioDto> listarFuncionarios() {

		List<FuncionarioEntity> listaFuncionarioEntity = repo.findAll();

		List<FuncionarioDto> listaFuncionarioDto = new ArrayList<FuncionarioDto>();

		for (FuncionarioEntity f : listaFuncionarioEntity) {

			FuncionarioDto funcionarioDto = new FuncionarioDto();

			funcionarioDto.setIdFuncionario(f.getIdFuncionario());
			funcionarioDto.setNome(f.getNome());
			funcionarioDto.setCpf(f.getCpf());
			funcionarioDto.setEmail(f.getEmail());
			funcionarioDto.setSenha(f.getSenha());
			funcionarioDto.setDataNascimento(f.getDataNascimento());
			funcionarioDto.setSalario(f.getSalario());
			funcionarioDto.setGerente(f.isGerente());

			funcionarioDto.setEndereco(new EnderecoDto());
			funcionarioDto.getEndereco().setEstado(f.getEndereco().getEstado());
			funcionarioDto.getEndereco().setCidade(f.getEndereco().getCidade());
			funcionarioDto.getEndereco().setBairro(f.getEndereco().getBairro());
			funcionarioDto.getEndereco().setLogradouro(f.getEndereco().getLogradouro());
			funcionarioDto.getEndereco().setNumero(f.getEndereco().getNumero());
			funcionarioDto.getEndereco().setCep(f.getEndereco().getCep());

			funcionarioDto.setDepartamento(new DepartamentoFuncionarioDto());
			funcionarioDto.getDepartamento().setIdDepartamento(f.getDepartamento().getIdDepartamento());
			funcionarioDto.getDepartamento().setNmDepartamento(f.getDepartamento().getNmDepartamento());

			if (f.getCriadoPor() != null) {
				funcionarioDto.setCriadoPor(f.getCriadoPor().getNome());
			}

			listaFuncionarioDto.add(funcionarioDto);
		}

		return listaFuncionarioDto;
	}

	@PreAuthorize("hasAnyRole('FUNCIONARIO', 'GERENTE')")
	public FuncionarioDto obterFuncionarioPorId(int id) {

		FuncionarioEntity funcionarioEntity = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

		FuncionarioDto funcionarioDto = new FuncionarioDto();

		funcionarioDto.setIdFuncionario(funcionarioEntity.getIdFuncionario());
		funcionarioDto.setNome(funcionarioEntity.getNome());
		funcionarioDto.setCpf(funcionarioEntity.getCpf());
		funcionarioDto.setEmail(funcionarioEntity.getEmail());
		funcionarioDto.setSenha(funcionarioEntity.getSenha());
		funcionarioDto.setDataNascimento(funcionarioEntity.getDataNascimento());
		funcionarioDto.setSalario(funcionarioEntity.getSalario());
		funcionarioDto.setGerente(funcionarioEntity.isGerente());

		funcionarioDto.setEndereco(new EnderecoDto());
		funcionarioDto.getEndereco().setEstado(funcionarioEntity.getEndereco().getEstado());
		funcionarioDto.getEndereco().setCidade(funcionarioEntity.getEndereco().getCidade());
		funcionarioDto.getEndereco().setBairro(funcionarioEntity.getEndereco().getBairro());
		funcionarioDto.getEndereco().setLogradouro(funcionarioEntity.getEndereco().getLogradouro());
		funcionarioDto.getEndereco().setNumero(funcionarioEntity.getEndereco().getNumero());
		funcionarioDto.getEndereco().setCep(funcionarioEntity.getEndereco().getCep());

		funcionarioDto.setDepartamento(new DepartamentoFuncionarioDto());
		funcionarioDto.getDepartamento().setIdDepartamento(funcionarioEntity.getDepartamento().getIdDepartamento());
		funcionarioDto.getDepartamento().setNmDepartamento(funcionarioEntity.getDepartamento().getNmDepartamento());

		if (funcionarioEntity.getCriadoPor() != null) {
			funcionarioDto.setCriadoPor(funcionarioEntity.getCriadoPor().getNome());
		}

		return funcionarioDto;
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void atualizarFuncionario(FuncionarioDto funcionarioDto) {

		FuncionarioEntity funcionarioEntity = repo.findById(funcionarioDto.getIdFuncionario())
				.orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

		if (repo.existsByEmailAndIdFuncionarioNot(funcionarioDto.getEmail(), funcionarioDto.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		if (repo.existsByCpfAndIdFuncionarioNot(funcionarioDto.getCpf(), funcionarioDto.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse CPF já existe");
		}

		DepartamentoEntity departamentoEntity = departamentoRepo
				.findById(funcionarioDto.getDepartamento().getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

		funcionarioEntity.setNome(funcionarioDto.getNome());
		funcionarioEntity.setCpf(funcionarioDto.getCpf());
		funcionarioEntity.setEmail(funcionarioDto.getEmail());
		funcionarioEntity.setSenha(funcionarioDto.getSenha());
		funcionarioEntity.setDataNascimento(funcionarioDto.getDataNascimento());
		funcionarioEntity.setSalario(funcionarioDto.getSalario());
		funcionarioEntity.setGerente(funcionarioDto.isGerente());

		funcionarioEntity.setEndereco(new EnderecoEntity());

		funcionarioEntity.getEndereco().setEstado(funcionarioDto.getEndereco().getEstado());
		funcionarioEntity.getEndereco().setCidade(funcionarioDto.getEndereco().getCidade());
		funcionarioEntity.getEndereco().setBairro(funcionarioDto.getEndereco().getBairro());
		funcionarioEntity.getEndereco().setLogradouro(funcionarioDto.getEndereco().getLogradouro());
		funcionarioEntity.getEndereco().setNumero(funcionarioDto.getEndereco().getNumero());
		funcionarioEntity.getEndereco().setCep(funcionarioDto.getEndereco().getCep());
		funcionarioEntity.setDepartamento(departamentoEntity);

		repo.save(funcionarioEntity);
	}

	@PreAuthorize("hasRole('GERENTE')")
	public void deletarFuncionario(int id) {

		repo.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

		repo.deleteById(id);
	}

}
