package com.delegrego.exemplo_spring_boot_2.dto.departamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartamentoDto {

	private int idDepartamento;

	@NotBlank
	@Size(max = 50)
	private String nmDepartamento;

	public DepartamentoDto() {

	}

	public DepartamentoDto(int idDepartamento, String nome) {
		this.idDepartamento = idDepartamento;
		this.nmDepartamento = nome;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNmDepartamento() {
		return nmDepartamento;
	}

	public void setNmDepartamento(String nmDepartamento) {
		this.nmDepartamento = nmDepartamento;
	}

	

}
