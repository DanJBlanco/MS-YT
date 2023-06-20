package com.djblanco.securityjwt.controller;

import com.djblanco.securityjwt.models.ERole;
import com.djblanco.securityjwt.models.RoleEntity;
import com.djblanco.securityjwt.models.UserEntity;
import com.djblanco.securityjwt.repository.UserRepository;
import com.djblanco.securityjwt.request.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public PrincipalController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello not auth";
    }

    @GetMapping("/hello-auth")
    public String helloAuth(){
        return "Hello Auth";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(rol -> {
                    return RoleEntity.builder().name(ERole.valueOf(rol)).build();
                }
                )
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam String id) {

        userRepository.deleteById(Long.parseLong(id));
        return "User delete with id: " + id;

    }
}
