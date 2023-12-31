package com.sinensia.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Nuestra clase de configuracion
 * 
 * @author Sergio
 * @see com.sinensia.service.InscripcionService
 * @see com.sinensia.service.InscripcionServiceImpl
 */
@EntityScan("com.sinensia.model")
@EnableJpaRepositories("com.sinensia.dao")
@SpringBootApplication(scanBasePackages = { "com.sinensia.controller", "com.sinensia.service" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Creamos el @Bean para poder inyectarlo en otras parte de la aplicacion
	 * 
	 * @return
	 */
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
