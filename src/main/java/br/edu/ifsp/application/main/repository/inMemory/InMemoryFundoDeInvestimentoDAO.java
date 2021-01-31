package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;

import java.util.*;

public class InMemoryFundoDeInvestimentoDAO implements FundoDeInvestimentoDAO {
    private static Map<Integer, Ativo> db;
    private static int idCounter;

    public InMemoryFundoDeInvestimentoDAO(Map<Integer, Ativo> db) {
        InMemoryFundoDeInvestimentoDAO.db = db;
    }

    @Override
    public Integer create(FundoDeInvestimento fundoDeInvestimento) {
        idCounter++;
        fundoDeInvestimento.setId(idCounter);
        db.put(idCounter, fundoDeInvestimento);
        return idCounter;
    }

    @Override
    public Optional<FundoDeInvestimento> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of((FundoDeInvestimento) db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<FundoDeInvestimento> findAll() {
        ArrayList<FundoDeInvestimento> fundoDeInvestimentoList = new ArrayList<>();

        for (Ativo a : db.values()) {
            if(a instanceof FundoDeInvestimento)
                fundoDeInvestimentoList.add((FundoDeInvestimento) a);
        }

        return fundoDeInvestimentoList;
    }

    @Override
    public boolean update(FundoDeInvestimento fundoDeInvestimento) {
        Integer id = fundoDeInvestimento.getId();
        if(db.containsKey(id)) {
            db.replace(id, fundoDeInvestimento);

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
    public boolean delete(FundoDeInvestimento fundoDeInvestimento) {
        return deleteByKey(fundoDeInvestimento.getId());
    }

    @Override
    public List<FundoDeInvestimento> findAllByGrupo() {
        return null;
    }
}
