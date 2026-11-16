package com.delegrego.exemplo_spring_boot_2.security;


public record FuncionarioAutenticadoResponse(
    String email,
    String nome,
    String role
) {}