package com.delegrego.exemplo_spring_boot_2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_spring_boot_2.entity.DepartamentoEntity;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

	// Derived queries

	List<DepartamentoEntity> findByNmDepartamentoContaining(String nome);

}
