# Imagem base para o Spring Boot 2 com Java 21
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Configurações do banco de dados
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/exemplo_spring_boot_2
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=aluno

# Copia o arquivo jar para o container
COPY target/exemplo-spring-boot-2-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
