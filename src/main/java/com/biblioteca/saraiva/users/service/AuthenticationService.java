package com.biblioteca.saraiva.users.service;

import java.util.Set;

import com.biblioteca.saraiva.config.TokenProvider;
import com.biblioteca.saraiva.users.dto.LoginRequestDto;
import com.biblioteca.saraiva.users.dto.RegisterRequestDto;
import com.biblioteca.saraiva.users.dto.TokenResponseDto;
import com.biblioteca.saraiva.users.enums.RolesType;
import com.biblioteca.saraiva.users.models.RolesEntity;
import com.biblioteca.saraiva.users.models.Users;
import com.biblioteca.saraiva.users.repository.RolesRepository;
import com.biblioteca.saraiva.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private long expiration=900000;

    public void register(RegisterRequestDto registerRequestDto) throws BadRequestException {

        log.info("Tentando registrar usuário com email: {}", registerRequestDto.getEmail());

        Users user = userRepository.findByEmail(registerRequestDto.getEmail())
                .orElse(null);

        if (user != null) {
            log.warn("Tentativa de registro com email já existente: {}", registerRequestDto.getEmail());
            throw new BadRequestException("Usuário já cadastrado");
        }

        RolesEntity role = rolesRepository.findByNome(RolesType.ROLE_LEITOR.name())
                .orElseGet(() -> {
                    log.info("Role ROLE_LEITOR não encontrada, criando nova");
                    return rolesRepository.save(
                            RolesEntity.builder()
                                    .nome(RolesType.ROLE_LEITOR.name())
                                    .build()
                    );
                });

        userRepository.save(
                Users.builder()
                        .nome(registerRequestDto.getNome())
                        .email(registerRequestDto.getEmail())
                        .senha(passwordEncoder.encode(registerRequestDto.getSenha()))
                        .roles(Set.of(role))
                        .build()
        );

        log.info("Usuário registrado com sucesso: {}", registerRequestDto.getEmail());
    }

    public TokenResponseDto login(LoginRequestDto dto) throws Exception {

        log.info("Tentativa de login para email: {}", dto.getEmail());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
            );

            String token = tokenProvider.gerarToken(authentication);

            log.info("Login realizado com sucesso para: {}", dto.getEmail());

            return new TokenResponseDto(token, expiration);

        } catch (BadCredentialsException e) {

            log.warn("Falha no login (credenciais inválidas) para: {}", dto.getEmail());

            throw new BadRequestException("credenciais inválidas");

        } catch (Exception e) {

            log.error("Erro inesperado no login para: {}", dto.getEmail(), e);

            throw e;
        }
    }
}