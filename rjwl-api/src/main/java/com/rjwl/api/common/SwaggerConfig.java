package com.rjwl.api.common;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author aniu
 */
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = input -> {
            //只有添加了ApiOperation注解的method才在API中显示
            if (input.isAnnotatedWith(ApiOperation.class)){
                return true;
            }
            return false;
        };

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("aniu", "", "aniu101372@163.com");

        return new ApiInfoBuilder()
                .title("RUIJING")
                .description("RESTFul API 文档")
                .termsOfServiceUrl("http://localhost:8081/swagger-ui.html")
                .license("© 2019-2025 aniu. All rights reserved.")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
