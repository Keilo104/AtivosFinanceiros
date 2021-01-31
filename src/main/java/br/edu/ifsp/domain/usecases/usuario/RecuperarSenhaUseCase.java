package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.DAOs.TokenDAO;
import br.edu.ifsp.domain.DAOs.UsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Token;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.sendEmail;

import java.time.LocalDateTime;
import java.util.Optional;

public class RecuperarSenhaUseCase {
    private UsuarioDAO usuarioDAO;
    private TokenDAO tokenDAO;

    public RecuperarSenhaUseCase( UsuarioDAO usuarioDAO, TokenDAO tokenDAO) {
        this.usuarioDAO = usuarioDAO;
        this.tokenDAO = tokenDAO;
    }

    public boolean enviarToken(String cpf){
        Optional<Usuario> us = usuarioDAO.findOne(cpf);
        if (us.isPresent()){
            Usuario usuario = us.get();
            Token token = new Token(usuario, LocalDateTime.now());
            String check = String.valueOf(tokenDAO.create(token));
            if (!check.isEmpty()){
                sendEmail se = new sendEmail();
                se.send(usuario.getEmail(), check);
                return true;
            }
        }
        return false;
    }

    public boolean verificarToken(String token){
        if(tokenDAO.findIfExists(token)){
            tokenDAO.deleteByKey(token);
            System.out.println(token);
            return true;
        }
        return false;
    }
}
