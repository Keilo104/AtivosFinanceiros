package br.edu.ifsp.domain.entities.grupo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.usuario.Usuario;

import java.util.Iterator;
import java.util.List;

public class Grupo {
    private String nome;
    private float total;

    private List<Ativo> listaAtivos;

    public Grupo() {
    }

    public Grupo(String nome, float total) {
        this.nome = nome;
        this.total = total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void addAtivo(Ativo ativo) {
        this.listaAtivos.add(ativo);
    }

    public void removeAtivo(Ativo ativo) {
        this.listaAtivos.remove(ativo);
    }

    public void removeGrupo(int idx) {
        this.listaAtivos.remove(idx);
    }

    public Iterator<Ativo> getIteratorAtivos() {
        return this.listaAtivos.iterator();
    }
}
