package com.delegrego.exemplo_spring_boot_2.dto.departamento.response;

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
public class DepartamentoFuncionarioDto {

	private int idDepartamento;

	private String nmDepartamento;

}
