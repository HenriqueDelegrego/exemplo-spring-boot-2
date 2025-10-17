package com.delegrego.exemplo_spring_boot_2.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id

	// Define que o valor do campo será gerado automaticamente pelo banco
	// (AUTO_INCREMENT)
	// IDENTITY é o tipo utilizado pelo MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFuncionario;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	// Restringe o atributo para não ser nulo, ser único
	// E ter limite de 11 caracteres
	@Column(name = "cpf", length = 11, nullable = false, unique = true)
	private String cpf;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "senha", length = 100, nullable = false)
	private String senha;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@Column(name = "salario", precision = 10, scale = 2)
	private BigDecimal salario;

	@Column(name = "gerente", nullable = false)
	private boolean gerente;

	// Indica que o campo abaixo é um objeto incorporado
	@Embedded
	private Endereco endereco;

	// Indica relacionamento muitos-para-um com Departamento
	@ManyToOne
	// Mapeia a chave estrangeira para departamento
	// name é o nome da coluna na tabela funcionario
	// referencedColumnName é o nome da coluna na tabela departamento
	@JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(name = "criado_por", nullable = true)
	private Funcionario criadoPor;

	public Funcionario() {

	}

	public Funcionario(int idFuncionario, String nome, String cpf, String email, String senha, LocalDate dataNascimento,
			BigDecimal salario, boolean gerente, Endereco endereco, Departamento departamento, Funcionario criadoPor) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.gerente = gerente;
		this.endereco = endereco;
		this.departamento = departamento;
		this.criadoPor = criadoPor;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Funcionario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Funcionario criadoPor) {
		this.criadoPor = criadoPor;
	}

}
