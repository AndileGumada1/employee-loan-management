package com.atlas.loan.application.configurations;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Employee Loan Management.")
                .version("1.0")
                .description("An online employee loan application where an employee can login, signup, apply a loan and track their transactions .")
                .contact(new Contact("Andile Gumada",
                        "https://github.com/AndileGumada1",
                        "decemberandile@email.com"))
                .license("Apache License Version 2.0")
                .build();
    }

}
