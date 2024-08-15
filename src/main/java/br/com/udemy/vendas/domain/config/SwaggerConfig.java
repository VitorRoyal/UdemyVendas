package br.com.udemy.vendas.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vendas API")
                        .version("1.0")
                        .description("API do projeto de Vendas")
                        .contact(new Contact()
                                .name("Vitor de Souza")
                                .url("https://github.com/VitorRoyal")
                                .email("vitorsouzaazuos@gmail.com")));
    }
}
