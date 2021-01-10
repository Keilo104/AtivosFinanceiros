package br.edu.ifsp.domain.usecases.grupo;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface GrupoDAO extends DAO<Grupo, Integer> {

    public Optional<Grupo> findOneByNome(String nome);
}