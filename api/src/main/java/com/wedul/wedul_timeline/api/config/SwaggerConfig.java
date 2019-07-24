package com.wedul.wedul_timeline.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-07-24
 **/
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wedul.wedul_timeline.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Timeline item Api",
                "timeline item api information.",
                "1.0.0",
                "Terms of service",
                new Contact("wedul", "wedul.site", "wedul.chul@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
