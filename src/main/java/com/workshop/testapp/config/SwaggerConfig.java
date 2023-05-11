package com.workshop.testapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI springWalletAPI(){
        final String security="bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(security))
                .components(new Components()
                        .addSecuritySchemes(security,
                                new SecurityScheme()
                                        .name(security)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Wallet API")
                        .description("Wallet Service API")
                        .version("1.0.0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }


}
