package com.delegrego.exemplo_spring_boot_2.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.delegrego.exemplo_spring_boot_2.model.Funcionario;

import java.util.Collection;
import java.util.List;

/**
 * Implementação de UserDetails para representar os detalhes do funcionário
 * autenticado no sistema.
 * 
 * Esta classe encapsula um objeto Funcionario e fornece as informações
 * necessárias para o Spring Security durante o processo de autenticação e
 * autorização.
 * 
 * @see UserDetails
 */
public class FuncionarioDetails implements UserDetails {

	private final Funcionario f;

	public FuncionarioDetails(Funcionario f) {
		this.f = f;
	}

	/**
	 * Obtém o nome do funcionario validado
	 * 
	 * @return nome do funcionário
	 */
	public String obterNome() {
		return f.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return f.getSenha();
	}

	@Override
	public String getUsername() {
		return f.getEmail();
	}
}
