package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.ativo.RendaFixa;
import br.edu.ifsp.domain.usecases.ativo.rendafixa.RendaFixaDAO;

import java.util.*;

public class InMemoryRendaFixaDAO implements RendaFixaDAO {
    private static final Map<Integer, RendaFixa> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(RendaFixa rendaFixa) {
        idCounter++;
        rendaFixa.setId(idCounter);
        db.put(idCounter, rendaFixa);
        return idCounter;
    }

    @Override
    public Optional<RendaFixa> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<RendaFixa> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(RendaFixa rendaFixa) {
        Integer id = rendaFixa.getId();
        if(db.containsKey(id)) {
            db.replace(id, rendaFixa);
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
    public boolean delete(RendaFixa rendaFixa) {
        return deleteByKey(rendaFixa.getId());
    }
}
