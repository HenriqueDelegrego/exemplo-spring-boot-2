package com.delegrego.exemplo_spring_boot_2.dto;

public class DepartamentoDto {

	private int idDepartamento;
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
