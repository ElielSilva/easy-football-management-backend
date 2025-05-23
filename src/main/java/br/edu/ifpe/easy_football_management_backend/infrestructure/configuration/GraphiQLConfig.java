package br.edu.ifpe.easy_football_management_backend.infrestructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GraphiQLConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/graphql/**")
                .addResourceLocations("classpath:/graphql/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
