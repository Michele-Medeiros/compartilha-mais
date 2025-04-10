package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.domain.Usuario;
import org.example.repository.UsuarioRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String exibirFormularioLogin() {
        return "login"; // nome da página login.html
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email,
                             @RequestParam String senha,
                             Model model,
                             HttpSession session) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.getSenha().equals(senha)) {
                // Sucesso no login: salva na sessão
                session.setAttribute("usuarioLogado", usuario);
                return "redirect:/"; // Redireciona para home
            } else {
                model.addAttribute("erro", "Senha incorreta.");
            }
        } else {
            model.addAttribute("erro", "Usuário não encontrado.");
        }

        return "login"; // volta pra tela de login
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

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


    @GetMapping("/register")
    public String register (Model model) {
        model.addAttribute("cadastro", new Usuario());
        return "register"; // nome do arquivo .html em resources/templates
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


    @PostMapping("/usuario/processar")
    public String processarUsuario(@ModelAttribute Usuario usuario,
                                   @RequestParam String acao,
                                   @RequestParam(required = false) String senhaConfirmacao,
                                   HttpSession session,
                                   Model model) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        if ("salvar".equals(acao)) {
            usuario.setId(usuarioLogado.getId()); // Garante atualização do usuário correto
            usuarioRepository.save(usuario);
            model.addAttribute("msg", "Perfil atualizado com sucesso!");
            return "redirect:/profile";

        } else if ("excluir".equals(acao)) {
            if (senhaConfirmacao == null || !senhaConfirmacao.equals(usuarioLogado.getSenha())) {
                model.addAttribute("erro", "Senha incorreta. Conta não foi excluída.");
                model.addAttribute("usuario", usuarioLogado);
                return "profile";
            }

            usuarioRepository.delete(usuarioLogado);
            session.invalidate();
            return "redirect:/login?logout";
        }

        return "redirect:/profile";
    }

}
