package com.api.brq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for implementing Swagger
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * General Swagger configuration method and feature implementation
     * @return new Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.brq"))
                .paths(PathSelectors.regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Method of setting information on the Swagger page
     * @return new ApiInfoBuilder
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API PERSON BRQ")
                .description("Api to Insert, Update, List and Delete Person")
                .build();
    }
}