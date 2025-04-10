package org.example.config;

import org.example.domain.Endereco;
import org.example.domain.Produto;
import org.example.domain.Usuario;
import org.example.repository.ProdutoRepository;
import org.example.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner carregarUsuario(UsuarioRepository usuarioRepository) {
        return args -> {
            if (!usuarioRepository.findByEmail("teste@email.com").isPresent()) {
                Usuario usuario = new Usuario();
                usuario.setEmail("teste@email.com");
                usuario.setSenha("123456"); // atenção: senha em texto plano, apenas para testes
                usuario.setTelefone(1234567);
                usuario.setNome("Ricardo");
                Endereco endereco = new Endereco();
                endereco.setCep(121212);
                endereco.setRua("Tuiuti");
                endereco.setBairro("Tatuape");
                endereco.setEstado("Sao Paulo");
                usuario.setEndereco(endereco);
                usuarioRepository.save(usuario);
                System.out.println("Usuário de teste criado!");
            }
        };
    }

    @Bean
    CommandLineRunner initData(ProdutoRepository produtoRepository) {
        return args -> {
            // Femininas
            produtoRepository.save(new Produto("Feminina", "M", "Rosa", "Novo", "Vestido de verão floral"));
            produtoRepository.save(new Produto("Feminina", "G", "Preto", "Usado", "Blusa de lã"));

            // Masculinas
            produtoRepository.save(new Produto("Masculina", "M", "Azul", "Novo", "Camisa social manga longa"));
            produtoRepository.save(new Produto("Masculina", "G", "Jeans", "Usado", "Bermuda jeans casual"));

            // Infantil
            produtoRepository.save(new Produto("Infantil", "P", "Amarelo", "Novo", "Macacão com estampa de animais"));
        };
    }
}
