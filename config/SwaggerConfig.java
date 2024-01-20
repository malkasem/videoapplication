package encoway.springframework.videoapplication.config;



/*
http://localhost:8080/swagger-ui/index.html
 */

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Security;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mohamad Alkasem",
                        email = "mohamad.alkasem@encoway.de"
                ),
                title = "Javaflix Video Application",
                description = "Video on Demand Application with Springframework",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http//localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "javaflix"
                )
        }
)
@SecurityScheme(
        name = "javaflix",
        description = "JWT authorisation",
        scheme = "Bearer Token for API",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
//@Configuration
public class SwaggerConfig {
 /*   @Bean
    public OpenAPI infoOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Javaflix Video Application")
                        .description("Video on Demand Application with Springframework")
                        .version("1.0.0"));
    }*/
}


