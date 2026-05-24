package com.biblioteca.saraiva.users.controller;

import com.biblioteca.saraiva.config.TokenBlacklist;
import com.biblioteca.saraiva.users.dto.LoginRequestDto;
import com.biblioteca.saraiva.users.dto.RegisterRequestDto;
import com.biblioteca.saraiva.users.dto.TokenResponseDto;
import com.biblioteca.saraiva.users.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Autentication", description = "Operações relacionadas ao login e registros")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final TokenBlacklist tokenBlacklist;

    @Operation(summary = "Registrar usuário")
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto registerRequestDto) throws Exception {
        authenticationService.register(registerRequestDto);

        }

    @Operation(summary = "Realizar login")
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception {
        return authenticationService.login(loginRequestDto);

    }

    @Operation(summary = "Realizar logout")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            tokenBlacklist.invalidate(token);
        }

        log.info("Logout realizado com sucesso");
        return ResponseEntity.ok().build();
    }
}

