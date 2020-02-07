package com.ProjectCC.dero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class DeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeroApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Configuration
	public static class PathMatchingConfigurationAdapter implements WebMvcConfigurer {

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			configurer.favorPathExtension(false);
		}
	}
}
