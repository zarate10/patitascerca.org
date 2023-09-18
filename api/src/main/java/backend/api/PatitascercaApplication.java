package backend.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatitascercaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatitascercaApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("PATITASCERCA API")
				.version("0.0.1")
				.description("Endpoints del proyecto 'PATITASCERCA', APLICACIONES INTERACTIVAS, UADE."));
	}
}
