package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.DAOs.AtivosDAO;

import java.util.*;

public class InMemoryAtivosDAO implements AtivosDAO {

    private static Map<Integer, Ativo> db;
    private static int idCounter;

    public InMemoryAtivosDAO(Map<Integer, Ativo> db) {
        InMemoryAtivosDAO.db = db;
    }

    @Override
    public Integer create(Ativo ativo) {
        idCounter++;
        ativo.setId(idCounter);
        db.put(idCounter, ativo);
        return idCounter;
    }

    @Override
    public Optional<Ativo> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<Ativo> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Ativo ativo) {
        Integer id = ativo.getId();
        if(db.containsKey(id)) {
            db.replace(id, ativo);
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
    public boolean delete(Ativo ativo) {
        return deleteByKey(ativo.getId());
    }
}
