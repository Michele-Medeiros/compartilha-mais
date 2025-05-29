package org.compartilha.service;

import org.compartilha.domain.Usuario;
import org.compartilha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarCadastro(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean deletarUsuarioSeSenhaCorreta(Usuario usuarioDaSessao, String senhaInformada) {
        if (usuarioDaSessao.getSenha().equals(senhaInformada)) {
            usuarioRepository.delete(usuarioDaSessao);
            return true;
        }
        return false;
    }

}
