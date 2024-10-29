package com.escola.escola.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configurable
@Configuration
public class Springdoc {

    @Bean
    public OpenAPI springShOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Escola com Documentação Completa")
                        .description("API Restful para gerenciamento de escola, incluindo professores e alunos.")
                        .version("1.1.0")
                        .contact(new Contact()
                                .name("Suporte API")
                                .url("http://localhost:8080/swagger-ui/index.html#/")
                                .email("suporte@escola.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório da Aplicação")
                        .url("https://github.com/brunoReinaldo96/ProjetoCRUDSpringBoot"));

    }

}
