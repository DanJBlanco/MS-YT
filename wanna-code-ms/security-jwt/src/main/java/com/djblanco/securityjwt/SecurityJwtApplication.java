package com.djblanco.securityjwt;

import com.djblanco.securityjwt.models.ERole;
import com.djblanco.securityjwt.models.RoleEntity;
import com.djblanco.securityjwt.models.UserEntity;
import com.djblanco.securityjwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SecurityJwtApplication {

	private final 	PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public SecurityJwtApplication(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtApplication.class, args);
	}


	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity userEntity = UserEntity.builder()
					.email("admin@email.com")
					.username("admin-user")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(
							RoleEntity.builder()
									.name(ERole.valueOf(ERole.ADMIN.name()))
									.build()
					))
					.build();


			UserEntity userEntity2 = UserEntity.builder()
					.email("user1@email.com")
					.username("user1")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(
							RoleEntity.builder()
									.name(ERole.valueOf(ERole.USER.name()))
									.build()
					))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("user2@email.com")
					.username("user2")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(
							RoleEntity.builder()
									.name(ERole.valueOf(ERole.STUDENT.name()))
									.build()
					))
					.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);
		};
	}

}
