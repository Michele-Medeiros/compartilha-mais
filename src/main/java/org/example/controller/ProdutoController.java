package org.example.controller;

import org.example.domain.Produto;
import org.example.repository.ProdutoRepository;
import org.example.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoRepository produtoRepository;

//    @PostMapping("/produto/cadastrar")
//    public String cadastrarProduto(@ModelAttribute Produto produto) {
//        service.salvarProduto(produto);
//        System.out.println("Produto cadastrado: " + produto.getCategoria());
//        return "redirect:/";
//    }

    //método para cadastrar a imagem tbm

    @PostMapping("/produto/cadastrar")
    public String cadastrarProduto(@ModelAttribute Produto produto,
                                   @RequestParam("imagens") MultipartFile[] imagens) {
        for (MultipartFile imagem : imagens) {
            if (!imagem.isEmpty()) {
                try {
                    // Gera um nome único para a imagem
                    String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();

                    // Caminho onde a imagem será salva
                    Path caminho = Paths.get("target/classes/static/images/" + nomeArquivo);
                    Files.write(caminho, imagem.getBytes());

                    // Atualiza o nome da imagem no produto
                    produto.setImagem(nomeArquivo);

                } catch (IOException e) {
                    e.printStackTrace();
                    // Se der erro, você pode redirecionar com ?erro ou exibir uma mensagem
                    return "redirect:/produto/cadastrar?erro";
                }
            }
        }

        // Aqui você salva o produto no banco (caso esteja usando JPA ou outro repositório)
        // exemplo: produtoRepository.save(produto);
        // Salva no banco de dados
        service.salvarProduto(produto);
        System.out.println("Produto cadastrado: " + produto.getCategoria());
        return "redirect:/produto/cadastrar?sucesso";
    }

    @GetMapping("/produto/cadastrar")
    public String exibirFormularioCadastro() {
        return "index"; // substitua pelo nome correto da sua view, sem a extensão .html
    }

    @GetMapping("/item-info/{id}")
    public String exibirDetalhesProduto(@PathVariable Long id, Model model) {
        Produto produto = service.buscarProdutoPorId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "item-info";
        } else {
            return "redirect:/produtos";
        }
    }

    @GetMapping("/items")
    public String exibirItens(@RequestParam(required = false) Long id, Model model) {
        List<Produto> produtos = service.listarTodos();
        model.addAttribute("produtos", produtos);

        if (id != null) {
            Produto produtoSelecionado = service.buscarProdutoPorId(id);
            if (produtoSelecionado != null) {
                model.addAttribute("produtoSelecionado", produtoSelecionado);
            }
        }

        return "items";
    }

    @GetMapping("/items/categoria")
    public String exibirItensCategoria(@RequestParam(required = false) String categoria, Model model) {
        List<Produto> produtos = (categoria != null && !categoria.isEmpty())
                ? service.buscarPorCategoria(categoria)
                : service.listarTodos();

        model.addAttribute("produtos", produtos);
        return "items";
    }

    @GetMapping("/solicitar")
    public String solicitarItemForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "request";
    }

//   @PostMapping("/produtos")
//    public String salvarProduto(@ModelAttribute Produto produto) {
//        produtoRepository.save(produto);
//        return "redirect:/produtos";
//    }

    @PostMapping("/produtos/deletar")
    public String deletarProduto(@ModelAttribute Produto produto) {
        if (produto.getId() != null) {
            produtoRepository.deleteById(produto.getId());
        }
        return "redirect:/"; // redireciona para a home
    }

    @GetMapping("/request")
    public String request(Model model){
        model.addAttribute("produto", new Produto()); // Adiciona o objeto esperado pelo formulário
        return "request";
    }

    @PostMapping("/request/{id}")
    public String solicitarDoacao(@PathVariable Long id, Model model) {
        Produto produto = service.buscarProdutoPorId(id);
            if (produto != null) {
                model.addAttribute("produto", produto);
//            produtoRepository.deleteById(id);
//            return ResponseEntity.ok("Produto solicitado e removido com sucesso!");
            return "request";
        }

            else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
                return "redirect:/items";
        }
    }

//    @PostMapping("/request")
//    public ResponseEntity<String> solicitarDoacao(@RequestParam Long id) {
//        Optional<Produto> produtoOptional = produtoRepository.findById(id);
//        if (produtoOptional.isPresent()) {
//            produtoRepository.deleteById(id);
//            return ResponseEntity.ok("Produto solicitado e removido com sucesso!");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
//        }
//    }
}
