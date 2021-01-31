package br.edu.ifsp.domain.DAOs;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.List;
import java.util.Optional;

public interface GrupoDAO extends DAO<Grupo, Integer> {

    Optional<Grupo> findOneByNome(String nome);

    Optional<Grupo> findOneByAtivo(Ativo ativo);

    Integer createComCPF(Grupo grupo, String cpf);

    List<Grupo> findAllByCpf(String cpf);

}