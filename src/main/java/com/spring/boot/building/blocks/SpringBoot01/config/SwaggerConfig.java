package com.spring.boot.building.blocks.SpringBoot01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean

    public LinkDiscoverers discoverers() {

        List<LinkDiscoverer> plugins = new ArrayList<>();

        plugins.add(new CollectionJsonLinkDiscoverer());

        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));



    }
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.spring.boot.building.blocks.SpringBoot01"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.ant("/users/**"))
                .build();
    }
    //Swagger meta-data url: http://localhost:8111/v2/api-docs
    //Swagger ui url:http://localhost:8080/swagger-ui.html

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("StackSimplify")
                .description("assd")
                .version("2.0")
                .contact(new Contact("Oshin","http://oshinbanerjee.com","wedfddc@hg.com"))
                .license("swedf")
                .licenseUrl("http://wserfvb.com")
                .build();
    }
}
