package com.ArtMar_Store.Api;

import com.ArtMar_Store.Api.api.users.UserRequestDto;
import com.ArtMar_Store.Api.domain.users.AppUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
