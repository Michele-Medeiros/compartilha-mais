package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.domain.Usuario;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CadastroController {

    @Autowired
    private UserService userService;


    @PostMapping("/cadastrar")
    public String criarCadastro(@Valid @ModelAttribute("usuario") Usuario usuario,
                                BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            model.addAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "cadastro"; // volta para a página de cadastro com erro
        }

        userService.salvarCadastro(usuario);
        return "redirect:/"; // redireciona para a home após sucesso
    }

    @PostMapping("/deletar-conta")
    public String deletarConta(@RequestParam("senha") String senha,
                               HttpSession session,
                               Model model) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            boolean deletado = userService.deletarUsuarioSeSenhaCorreta(usuarioLogado, senha);

            if (deletado) {
                session.invalidate(); // encerra a sessão
                return "redirect:/login?logout"; // redireciona após deletar
            } else {
                model.addAttribute("erro", "Senha incorreta. Conta não foi deletada.");
                return "profile"; // volta para o perfil com erro
            }
        } else {
            return "redirect:/login"; // se não estiver logado
        }
    }

}
