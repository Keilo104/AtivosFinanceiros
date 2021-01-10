package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento.FundoDeInvestimentoDAO;

import java.util.*;

public class InMemoryFundoDeInvestimentoDAO implements FundoDeInvestimentoDAO {
    private static final Map<Integer, FundoDeInvestimento> db = new LinkedHashMap<>();
    private static int idCounter;

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
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<FundoDeInvestimento> findAll() {
        return new ArrayList<>(db.values());
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
}
