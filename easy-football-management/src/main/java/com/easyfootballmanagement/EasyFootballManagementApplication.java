package com.easyfootballmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@OpenAPIDefinition
        (info = @Info(
        title = "EASY FOOTBALL MANAGER",
        version = "1.0",
        description = "backend responsavel por persistir dados de gestão de campeonatos",
        contact = @Contact(name = "Jadeilson Dev Backend",
                url = "https://api.whatsapp.com/send?phone=5581992167390",
                email = "jms@a.palmares.ifpe.edu.br"),
        license = @License
                (name = "Apache 2.0",
                url = "http://www.apache.org/licenses/LICENSE-2.0.html")
),
        servers = {
                @Server(url = "http://localhost:8090/", description = "Server local")}
)
@SpringBootApplication
public class EasyFootballManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyFootballManagementApplication.class, args);
    }

}
