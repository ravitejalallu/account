package com.homebank.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "Homebank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "ravi Teja"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "Homebank Accounts microservice REST API Documentation",
                url = "http://localhost:8080/swagger-ui.html"
        )
)
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
