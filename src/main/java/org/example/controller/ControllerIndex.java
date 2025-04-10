package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.domain.Usuario;
import org.example.repository.UsuarioRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ControllerIndex {

    @Autowired
    private UserService userService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index (){
        return "index";
    }


    @GetMapping("/donate")
    public String donate(){
        return "donate";
    }

    @GetMapping("/request")
    public String request(){
        return "request";
    }

    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }

    @GetMapping("/password_1")
    public String password_1(){
        return "password_1";
    }

    @GetMapping("/password_2")
    public String password_2(){
        return "password_2";
    }

    @GetMapping("/privacy")
    public String privacy(){
        return "privacy";
    }

    @GetMapping("/about_us")
    public String about_us(){
        return "about_us";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        // Recupera o usuário da sessão
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            // Busca pelo e-mail do usuário logado
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(usuarioLogado.getEmail());

            if (usuarioOpt.isPresent()) {
                model.addAttribute("usuario", usuarioOpt.get());
                return "profile"; // Página de perfil
            } else {
                model.addAttribute("erro", "Usuário não encontrado.");
                return "login"; // Redireciona para login
            }
        } else {
            return "redirect:/login"; // Se não estiver logado, vai para login
        }
    }

}
