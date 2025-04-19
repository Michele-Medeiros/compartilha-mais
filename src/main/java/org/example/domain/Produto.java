package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String tamanho;
    private String cor;
    private String tipo;
    private String descricao;

    private String imagem;

    public Produto(String categoria, String tamanho, String cor, String tipo, String descricao, String imagem) {
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.cor = cor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.imagem = imagem;
    }
}
