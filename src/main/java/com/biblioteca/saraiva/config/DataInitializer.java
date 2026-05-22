package com.biblioteca.saraiva.config;

import com.biblioteca.saraiva.users.models.RolesEntity;
import com.biblioteca.saraiva.users.models.Users;
import com.biblioteca.saraiva.users.repository.RolesRepository;
import com.biblioteca.saraiva.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RolesRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner init() {
        return args -> {

            // Criar roles
            if (!roleRepository.existsByNome("ROLE_ADMIN")) {
                roleRepository.save(new RolesEntity("ROLE_ADMIN"));
            }

            if (!roleRepository.existsByNome("ROLE_LEITOR")) {
                roleRepository.save(new RolesEntity("ROLE_LEITOR"));
            }

            // Criar admin
            if (!userRepository.existsByEmail("admin@email.com")) {

                RolesEntity adminRole = roleRepository.findByNome("ROLE_ADMIN")
                        .orElseThrow(() -> new RuntimeException("ROLE_ADMIN não encontrada"));

                Users admin = new Users();
                admin.setNome("Admin");
                admin.setEmail("admin@email.com");
                admin.setSenha(passwordEncoder.encode("123456"));
                admin.setRoles(Set.of(adminRole));

                userRepository.save(admin);
            }
        };
    }
}