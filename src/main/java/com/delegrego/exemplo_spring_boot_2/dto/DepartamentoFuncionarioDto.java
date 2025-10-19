package com.delegrego.exemplo_spring_boot_2.dto;

public class DepartamentoFuncionarioDto {

	private int idDepartamento;
	private String nmDepartamento;

	public DepartamentoFuncionarioDto() {
	}

	public DepartamentoFuncionarioDto(int idDepartamento, String nmDepartamento) {
		this.idDepartamento = idDepartamento;
		this.nmDepartamento = nmDepartamento;
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
