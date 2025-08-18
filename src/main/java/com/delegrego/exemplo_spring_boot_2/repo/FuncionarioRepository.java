package com.delegrego.exemplo_spring_boot_2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_spring_boot_2.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	// Derived query
	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpf(String cpf);

	// Utilizado para verificar se o CPF já existe, exceto para o funcionário com o ID fornecido
	boolean existsByCpfAndIdFuncionarioNot(String cpf, int id);

	// Utilizado para verificar se o email já existe, exceto para o funcionário com o ID fornecido
	boolean existsByEmailAndIdFuncionarioNot(String email, int id);
}
