package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.ativo.acao.AcaoDAO;

import java.util.*;

public class InMemoryAcaoDAO implements AcaoDAO {
    private static final Map<Integer, Acao> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Acao acao) {
        idCounter++;
        acao.setId(idCounter);
        db.put(idCounter, acao);
        return idCounter;
    }

    @Override
    public Optional<Acao> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<Acao> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Acao acao) {
        Integer id = acao.getId();
        if(db.containsKey(id)) {
            db.replace(id, acao);

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if(db.containsKey(key)) {
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Acao acao) {
        return deleteByKey(acao.getId());
    }
}
