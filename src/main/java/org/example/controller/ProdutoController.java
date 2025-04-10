package org.example.controller;

import org.example.domain.Produto;
import org.example.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/produto/cadastrar")
    public String cadastrarProduto(@ModelAttribute Produto produto) {
        service.salvarProduto(produto);
        System.out.println("Produto cadastrado: " + produto.getCategoria());
        return "redirect:/";
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
}
