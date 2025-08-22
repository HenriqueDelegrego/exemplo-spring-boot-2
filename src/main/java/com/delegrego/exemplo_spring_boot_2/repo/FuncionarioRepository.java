package com.delegrego.exemplo_spring_boot_2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_spring_boot_2.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	// Derived query

	/**
	 * Retorna um funcionário com o email fornecido
	 * @param email - O email do funcionário
	 * @return O funcionário, ou vazio se não encontrado
	 */
	Optional<Funcionario> findByEmail(String email);

	/**
	 * Retorna um funcionário com o cpf fornecido
	 * @param cpf - O cpf do funcionário
	 * @return O funcionário, ou vazio se não encontrado
	 */
	Optional<Funcionario> findByCpf(String cpf);

	/**
	 * Retorna um boolean indicando se o cpf já existe para outro funcionário
	 * 
	 * @param cpf - O cpf do funcionário
	 * @param id  - A id do funcionário
	 * @return Um boolean indicando se o cpf já existe para outro funcionário
	 *
	 */
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
