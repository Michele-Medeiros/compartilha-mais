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
                usuario.setNome("Bob Esponja");
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
            produtoRepository.save(new Produto("Roupa", "M", "Rosa", "Novo", "Blusa moletom", "blusa1-rosa.png"));
            produtoRepository.save(new Produto("Roupa", "P", "Preto", "Usado", "Saia", "saia-preta.png"));

            // Masculinas
            produtoRepository.save(new Produto("Roupa", "M", "Azul", "Novo", "Camiseta", "camiseta-azul.png"));
            produtoRepository.save(new Produto("Roupa", "G", "Jeans", "Usado", "Bermuda", "bermuda-casual.png"));
//
//            // Infantil
//            produtoRepository.save(new Produto("Masculino", "P", "Vermelho", "Novo", "Camiseta", "camiseta-vermelha.png"));
        };
    }
}
