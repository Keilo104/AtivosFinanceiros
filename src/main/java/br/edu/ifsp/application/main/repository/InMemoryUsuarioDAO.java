package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;

import java.util.*;

public class InMemoryUsuarioDAO implements UsuarioDAO{

    private static final Map<String, Usuario> db = new LinkedHashMap<>();

    @Override
    public boolean checkLogin(Usuario usuario) {
        Usuario u = findOneByEmail(usuario.getEmail()).orElse(null);
        if(u != null){
            return (u.getSenha().equals(usuario.getSenha()));
        }
        return false;
    }

    @Override
    public String create(Usuario usuario){
        db.put(usuario.getCpf(), usuario);
        return usuario.getCpf();
    }

    @Override
    public Optional<Usuario> findOne(String key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    public Optional<Usuario> findOneByEmail(String email){
        for(Usuario u : db.values()) {
            if(u.getEmail().equals(email)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Usuario usuario){
        String cpf = usuario.getCpf();
        if (db.containsKey(cpf)) {
            db.replace(cpf, usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if(db.containsKey(key)) {
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Usuario usuario){
        return deleteByKey(usuario.getCpf());
    }
}
