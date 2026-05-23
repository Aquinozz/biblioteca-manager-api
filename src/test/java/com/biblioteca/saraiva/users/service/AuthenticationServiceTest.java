package com.biblioteca.saraiva.users.service;

import com.biblioteca.saraiva.users.dto.LoginRequestDto;
import com.biblioteca.saraiva.users.dto.RegisterRequestDto;
import com.biblioteca.saraiva.users.dto.TokenResponseDto;
import com.biblioteca.saraiva.config.TokenProvider;
import com.biblioteca.saraiva.users.models.RolesEntity;
import com.biblioteca.saraiva.users.repository.RolesRepository;
import com.biblioteca.saraiva.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProvider tokenProvider;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RolesRepository rolesRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void deveRealizarLoginComSucesso() throws Exception {

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("admin@email.com");
        request.setSenha("123456");

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(tokenProvider.gerarToken(authentication))
                .thenReturn("token_fake");

        TokenResponseDto response = authenticationService.login(request);

        assertNotNull(response);
        assertEquals("token_fake", response.token());
    }

    @Test
    void deveFalharQuandoCredenciaisInvalidas() {

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("admin@email.com");
        request.setSenha("errada");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("erro"));

        assertThrows(org.apache.coyote.BadRequestException.class, () -> {
            authenticationService.login(request);
        });

        verify(tokenProvider, never()).gerarToken(any());
    }

    @Test
    void deveRegistrarUsuarioComSucesso() throws Exception {


        RegisterRequestDto request = new RegisterRequestDto();
        request.setNome("Murilo");
        request.setEmail("murilo@email.com");
        request.setSenha("123456");

        when(userRepository.findByEmail("murilo@email.com"))
                .thenReturn(java.util.Optional.empty());

        RolesEntity role = RolesEntity.builder()
                .nome("ROLE_LEITOR")
                .build();

        when(rolesRepository.findByNome("ROLE_LEITOR"))
                .thenReturn(java.util.Optional.of(role));

        when(passwordEncoder.encode("123456"))
                .thenReturn("senha_criptografada");

        authenticationService.register(request);

        verify(userRepository).save(argThat(user ->
                user.getEmail().equals("murilo@email.com") &&
                        user.getNome().equals("Murilo") &&
                        user.getSenha().equals("senha_criptografada") &&
                        user.getRoles().contains(role)
        ));
    }
}