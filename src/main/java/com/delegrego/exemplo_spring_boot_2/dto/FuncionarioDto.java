package com.delegrego.exemplo_spring_boot_2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class FuncionarioDto {

	private int idFuncionario;

	@NotBlank
	@Size(max = 100)
	private String nome;

	@NotBlank
	@Pattern(regexp = "\\d{8}")
	private String cpf;

	@NotBlank
	@Email
	@Size(max = 100)
	private String email;

	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;

	@Digits(integer = 10, fraction = 2)
	@PositiveOrZero
	private BigDecimal salario;

	private boolean gerente;

	@NotNull
	private EnderecoDto endereco;

	public FuncionarioDto() {

	}

	public FuncionarioDto(int idFuncionario, String nome, String cpf, String email, LocalDate dataNascimento,
			BigDecimal salario, boolean gerente, EnderecoDto endereco) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.gerente = gerente;
		this.endereco = endereco;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public boolean isGerente() {
		return gerente;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
}
