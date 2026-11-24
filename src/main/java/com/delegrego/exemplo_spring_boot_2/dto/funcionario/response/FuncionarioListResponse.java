package com.delegrego.exemplo_spring_boot_2.dto.funcionario.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.delegrego.exemplo_spring_boot_2.dto.endereco.response.EnderecoResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FuncionarioListResponse {

	private Long id;

	private String nome;

	private String cpf;

	private String email;

	private LocalDate dataNascimento;

	private BigDecimal salario;

	private String role;

	private EnderecoResponseDto endereco;

	private String departamento;

	private String criadoPor;

}
