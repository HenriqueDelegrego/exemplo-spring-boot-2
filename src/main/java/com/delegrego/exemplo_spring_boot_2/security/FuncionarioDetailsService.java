package com.delegrego.exemplo_spring_boot_2.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_spring_boot_2.model.Funcionario;
import com.delegrego.exemplo_spring_boot_2.repo.FuncionarioRepository;

// Classe para carregar informações do funcionário para autenticação

@Service
public class FuncionarioDetailsService implements UserDetailsService {

	private FuncionarioRepository repo;

	public FuncionarioDetailsService(FuncionarioRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Funcionario funcionario = repo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new User(funcionario.getEmail(), funcionario.getSenha(),
				Collections.singletonList(new SimpleGrantedAuthority("USER")));
	}
}