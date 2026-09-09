package com.biblioteca.saraiva.livros.dto;

import com.biblioteca.saraiva.livros.enums.EnumLivro;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class LivroRequest {

    @NotBlank(message = "O titulo deve ser preenchido")
    private String titulo;

    @NotBlank(message = "O autor deve ser definido")
    private String autor;

    private String descricao;

    @NotBlank(message = "A categoria deve ser definida")
    private EnumLivro categoria;

    @NotNull(message = "Defina o ano de criação")
    private Integer anoCriacao;

    @NotNull(message = "Defina preço")
    private BigDecimal preco;

    @NotNull (message = "Defina estoque")
    private Integer quantidade;
}
