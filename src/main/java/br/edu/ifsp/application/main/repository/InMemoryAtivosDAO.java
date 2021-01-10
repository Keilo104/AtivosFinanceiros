package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.usecases.ativo.AtivosDAO;

import java.util.*;

public class InMemoryAtivosDAO implements AtivosDAO {

    private static final Map<Integer, Ativo> db = new LinkedHashMap<>();
    private static int idCounter;

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