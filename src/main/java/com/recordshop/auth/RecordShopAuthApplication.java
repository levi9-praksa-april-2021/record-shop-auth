package com.recordshop.auth;

import com.recordshop.auth.domain.Role;
import com.recordshop.auth.domain.RoleRepository;
import com.recordshop.auth.domain.User;
import com.recordshop.auth.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@EnableDiscoveryClient
@SpringBootApplication
public class RecordShopAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopAuthApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Optional<User> user1 = userRepository.findByEmail("user@gmail.com");
			user1.ifPresent(user -> userRepository.deleteById(user.getId()));

			Role user = roleRepository.findByName("ROLE_USER");
			if (user == null)
				roleRepository.save(new Role(null, "ROLE_USER"));
			Role admin = roleRepository.findByName("ROLE_ADMIN");
			if (admin == null)
				admin = roleRepository.save(new Role(null, "ROLE_ADMIN"));
			Optional<User> admin1 = userRepository.findByEmail("admin@gmail.com");
			if (admin1.isEmpty())
				userRepository.save(new User(null, "admin@gmail.com", passwordEncoder.encode("admin123"), "Admin", "Admin", Collections.singletonList(admin)));
		};
	}

}
