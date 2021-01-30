package br.edu.ifsp.application.main.repository.inMemory;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;

import java.util.*;

public class InMemoryGrupoDAO implements GrupoDAO {
    private static final Map<Integer, Grupo> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Grupo grupo) {
        if(findOneByNome(grupo.getNome()).isEmpty()) {
            idCounter++;
            grupo.setId(idCounter);
            db.put(idCounter, grupo);
            return idCounter;
        }

        return 0;
    }

    @Override
    public Optional<Grupo> findOneByNome(String nome) {
        for (Grupo g : db.values()) {
            if(g.getNome().equals(nome)) {
                return Optional.of(g);
            }
        }

        return Optional.empty();
    }

    public Optional<Grupo> findOneByAtivo(Ativo ativo) {
        for (Grupo g : db.values()) {
            Iterator<Ativo> ativoIterator = g.getIteratorAtivos();
            while(ativoIterator.hasNext()) {
                if(ativoIterator.next().equals(ativo)) {
                    return Optional.of(g);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Integer createComCPF(Grupo grupo, String cpf) {
        if(findOneByNome(grupo.getNome()).isEmpty()) {
            idCounter++;
            grupo.setId(idCounter);
            db.put(idCounter, grupo);
            return idCounter;
        }

        return 0;
    }

    @Override
    public List<Grupo> findAllByCpf(String cpf) {
        return null;
    }

    @Override
    public Optional<Grupo> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }


    @Override
    public List<Grupo> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Grupo grupo) {
        Integer id = grupo.getId();
        if(db.containsKey(id)) {
            db.replace(id, grupo);
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
    public boolean delete(Grupo grupo) {
        return deleteByKey(grupo.getId());
    }
}
