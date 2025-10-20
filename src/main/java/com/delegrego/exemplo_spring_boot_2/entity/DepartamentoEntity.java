package com.delegrego.exemplo_spring_boot_2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class DepartamentoEntity {

	@Id

	// Define que o valor do campo será gerado automaticamente pelo banco
	// (AUTO_INCREMENT)
	// IDENTITY é o tipo utilizado pelo MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento")
	private int idDepartamento;

	// Restringe o atributo para não ser nulo e ter limite de 50 caracteres
	@Column(name = "nm_departamento", nullable = false, length = 50)
	private String nmDepartamento;

	public DepartamentoEntity(int idDepartamento, String nmDepartamento) {
		this.idDepartamento = idDepartamento;
		this.nmDepartamento = nmDepartamento;
	}

	public DepartamentoEntity() {

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
