package com.delegrego.exemplo_spring_boot_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.dto.FuncionarioDto;
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

		DepartamentoEntity departamentoEntity = departamentoRepo.findById(funcionarioDto.getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		FuncionarioEntity criadoPor = repo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		// Load creator entity
		// FuncionarioEntity criadoPor = repo.findByUsername(username).orElseThrow(() ->
		// new UsernameNotFoundException("User not found"));

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
