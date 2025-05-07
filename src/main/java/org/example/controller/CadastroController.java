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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CadastroController {

    @Autowired
    private UserService userService;


    @PostMapping("/cadastrar")
    public String criarCadastro(@Valid @ModelAttribute("usuario") Usuario usuario,
                                BindingResult result,
                                Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Mensagem de erro vai para o modelo da mesma requisição
            model.addAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "cadastro"; // Retorna para a mesma view com os erros
        }

        userService.salvarCadastro(usuario);

        // Mensagem de sucesso para próxima requisição
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "redirect:/cadastro";
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
