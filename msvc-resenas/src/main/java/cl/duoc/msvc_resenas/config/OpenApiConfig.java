package cl.duoc.msvc_resenas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server gatewayServer = new Server();
        gatewayServer.setUrl("http://localhost:8090");
        gatewayServer.setDescription("API Gateway - EventFlow");

        Contact contact = new Contact();
        contact.setName("Jahir Pineda");
        contact.setEmail("jahir@eventflow.cl");

        Info info = new Info()
                .title("Microservicio Reseñas - EventFlow")
                .description("API REST para gestión de reseñas y preguntas frecuentes de la plataforma EventFlow.")
                .version("1.0.0")
                .contact(contact)
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(gatewayServer));
    }
}