package com.delegrego.exemplo_spring_boot_2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartamentoDto {

	private int idDepartamento;

	@NotBlank
	@Size(max = 50)
	private String nome;

	public DepartamentoDto() {

	}

	public DepartamentoDto(int idDepartamento, String nome) {
		this.idDepartamento = idDepartamento;
		this.nome = nome;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
