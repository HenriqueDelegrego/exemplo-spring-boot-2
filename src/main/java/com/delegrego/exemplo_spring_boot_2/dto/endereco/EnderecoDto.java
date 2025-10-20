package com.delegrego.exemplo_spring_boot_2.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnderecoDto {

	@NotBlank
	@Size(min = 2, max = 2)
	private String estado;

	@NotBlank
	@Size(max = 100)
	private String cidade;

	@NotBlank
	@Size(max = 100)
	private String bairro;

	@NotBlank
	@Size(max = 100)
	private String logradouro;

	@NotNull
	@Size(max = 10)
	@Pattern(regexp = "\\d+")
	private String numero;

	@NotBlank
	@Pattern(regexp = "\\d{8}")
	private String cep;

	public EnderecoDto() {

	}

	public EnderecoDto(String estado, String cidade, String bairro, String logradouro, String numero, String cep) {
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "EnderecoDto [estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro + ", logradouro="
				+ logradouro + ", numero=" + numero + ", cep=" + cep + "]";
	}
	
	
	
}
