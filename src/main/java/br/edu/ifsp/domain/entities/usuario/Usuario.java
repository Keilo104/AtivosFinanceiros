package br.edu.ifsp.domain.entities.usuario;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.utils.Observer;
import br.edu.ifsp.domain.usecases.utils.Subject;

import java.util.Iterator;
import java.util.List;

public class Usuario implements Observer {
    private String cpf;
    private String email;
    private String senha;

    private float totalLucrado;
    private float totalInvestido;

    private float lucroPotencial;
    private float valorAtual;
    private float investimentoAtual;

    private List<Grupo> carteira;

    public Usuario() {
    }

    public Usuario(String cpf, String email, String senha) {
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String senha, String email) {
        this("", senha, email, 0, 0);
    }

    public Usuario(String cpf, String email, String senha, float totalLucrado, float totalInvestido) {
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public void addGrupo(Grupo grupo) {
        this.carteira.add(grupo);
    }

    public void removeGrupo(Grupo grupo) {
        this.carteira.remove(grupo);
    }

    public void removeGrupo(int idx) {
        this.carteira.remove(idx);
    }

    public Iterator<Grupo> getIteratorCarteira() {
        return this.carteira.iterator();
    }

    private void updateLucroTotalHistorico() {
        this.totalLucrado = 0;

        for (Grupo g : carteira) {
            this.totalLucrado += g.getTotalLucrado();
        }
    }

    private void updateInvestimentoTotalHistorico() {
        this.totalInvestido = 0;

        for (Grupo g : carteira) {
            this.totalInvestido += g.getTotalInvestido();
        }
    }

    private void updateLucroPotencial() {
        this.lucroPotencial = this.valorAtual - this.investimentoAtual;
    }

    private void updateValorAtual() {
        this.valorAtual = 0;

        for (Grupo g : carteira) {
            this.valorAtual += g.getValorAtual();
        }
    }

    private void updateInvestimentoAtual() {
        this.investimentoAtual = 0;

        for (Grupo g : carteira) {
            this.investimentoAtual += g.getInvestimentoAtual();
        }
    }

    @Override
    public void update(Subject o) {
        this.updateInvestimentoAtual();
        this.updateValorAtual();
        this.updateLucroPotencial();

        this.updateInvestimentoTotalHistorico();
        this.updateLucroTotalHistorico();
    }
}
