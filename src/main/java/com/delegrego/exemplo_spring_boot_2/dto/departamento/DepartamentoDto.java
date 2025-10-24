package com.delegrego.exemplo_spring_boot_2.dto.departamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class DepartamentoDto {

	private int idDepartamento;

	@NotBlank
	@Size(max = 50)
	private String nmDepartamento;

}
