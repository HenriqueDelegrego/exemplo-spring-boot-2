package com.delegrego.exemplo_spring_boot_2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_spring_boot_2.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	// Derived query
	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpf(String cpf);

	// Utilizado para verificar se o CPF já existe, exceto para o funcionário com o
	// ID fornecido
	boolean existsByCpfAndIdFuncionarioNot(String cpf, int id);

	/**
	 * Retorna um boolean indicando se o email já existe para outro funcionário
	 * diferente do fornecido.
	 * 
	 * @param email - O email do funcionário
	 * @param id    - A id do funcionário
	 * @return Um boolean indicando se o email já existe para outro funcionário
	 *         diferente do fornecido
	 *
	 */
	boolean existsByEmailAndIdFuncionarioNot(String email, int id);
}
