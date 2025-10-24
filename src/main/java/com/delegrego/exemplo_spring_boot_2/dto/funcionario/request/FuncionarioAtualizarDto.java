package com.delegrego.exemplo_spring_boot_2.dto.funcionario.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.delegrego.exemplo_spring_boot_2.dto.endereco.EnderecoDto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
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
public class FuncionarioAtualizarDto {

	private int idFuncionario;

	@NotBlank
	@Size(max = 100)
	private String nome;

	@NotBlank
	@Pattern(regexp = "\\d{11}")
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

	private int idDepartamento;

}
