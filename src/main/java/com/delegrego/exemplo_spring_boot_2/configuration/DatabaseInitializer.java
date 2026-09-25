package com.delegrego.exemplo_spring_boot_2.configuration;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

	private final JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() throws Exception {

		Resource resource = new ClassPathResource("data.sql");

		String sql = resource.getContentAsString(StandardCharsets.UTF_8);

		jdbcTemplate.execute(sql);

	}
}