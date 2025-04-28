package br.edu.ifpe.easy_football_management_backend.infrestructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurations implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200, http://localhost:5173, http://localhost:8080")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.TRACE.name(),
                        HttpMethod.HEAD.name()
                ).allowedHeaders(HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT,
                        HttpHeaders.AGE,
                        HttpHeaders.ALLOW,
                        HttpHeaders.MAX_FORWARDS,
                        HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS,
                        HttpHeaders.ACCEPT_RANGES,
                        HttpHeaders.AUTHORIZATION)
                .allowCredentials(true)
                .maxAge(3600);
    }
}