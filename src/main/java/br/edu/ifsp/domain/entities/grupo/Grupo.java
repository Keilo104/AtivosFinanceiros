package br.edu.ifsp.domain.entities.grupo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.usecases.utils.Observer;
import br.edu.ifsp.domain.usecases.utils.Subject;

import java.util.Iterator;
import java.util.List;

public class Grupo extends Subject implements Observer {
    private int id;
    private String nome;

    private float totalLucrado;
    private float totalInvestido;

    private float lucroPotencial;
    private float valorAtual;
    private float investimentoAtual;

    private List<Ativo> listaAtivos;
    private List<LogTransacaoAtivo> historico;

    public Grupo() {
    }

    public Grupo(String nome) {
        this.nome = nome;
    }

    public Grupo(int id, String nome, float totalLucrado, float totalInvestido) {
        this.id = id;
        this.nome = nome;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTotalLucrado() {
        return totalLucrado;
    }

    public float getTotalInvestido() {
        return totalInvestido;
    }

    public float getLucroPotencial() {
        return lucroPotencial;
    }

    public float getValorAtual() {
        return valorAtual;
    }

    public float getInvestimentoAtual() {
        return investimentoAtual;
    }

    public void addAtivo(Ativo ativo) {
        addInvestimento(ativo.getValorTotalAtual());

        this.listaAtivos.add(ativo);
        this.updateValorAtual();
    }

    public void removeAtivo(int idx) {
        this.removeAtivo(listaAtivos.get(idx));
    }

    public void removeAtivo(Ativo ativo) {
        addLucroTotalHistorico(ativo.getValorAtual() - ativo.getValorComprado());
        removeInvestimentoAtual(ativo.getValorTotalComprado());

        this.listaAtivos.remove(ativo);
        this.updateValorAtual();
    }

    public Iterator<Ativo> getIteratorAtivos() {
        return this.listaAtivos.iterator();
    }

    private void updateLucroPotencial() {
        this.lucroPotencial = this.valorAtual - this.investimentoAtual;
    }

    private void updateValorAtual() {
        this.valorAtual = 0;

        for (Ativo a : listaAtivos) {
            this.valorAtual += a.getValorAtual();
        }

        updateLucroPotencial();
        notifyObservers();
    }

    private void addLucroTotalHistorico(float lucro) {
        this.totalLucrado += lucro;
    }

    private void addInvestimento(float investimento) {
        this.investimentoAtual += investimento;
        this.totalInvestido += investimento;
    }

    private void removeInvestimentoAtual(float investimento) {
        this.investimentoAtual -= investimento;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", totalLucrado=" + totalLucrado +
                ", totalInvestido=" + totalInvestido +
                ", lucroPotencial=" + lucroPotencial +
                ", valorAtual=" + valorAtual +
                ", investimentoAtual=" + investimentoAtual +
                "} " + super.toString();
    }

    @Override
    public void update(Subject o) {
        this.updateValorAtual();
    }
}
