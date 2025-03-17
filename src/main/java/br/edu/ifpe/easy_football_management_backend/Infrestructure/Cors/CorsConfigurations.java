package br.edu.ifpe.easy_football_management_backend.Infrestructure.Cors;//package br.edu.ifpe.easy_football_management_backend.Infrestructure.Cors;

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
                .allowedOrigins("http://localhost:5173/")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.TRACE.name(),
                        HttpMethod.HEAD.name()
                ).allowedHeaders(HttpHeaders.CONTENT_TYPE,
                       HttpHeaders.AUTHORIZATION)
               .allowCredentials(true);
    }
}