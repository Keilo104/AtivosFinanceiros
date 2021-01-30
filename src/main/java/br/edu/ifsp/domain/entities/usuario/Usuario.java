package br.edu.ifsp.domain.entities.usuario;

import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.log.Log;
import br.edu.ifsp.domain.entities.log.LogGrupo;
import br.edu.ifsp.domain.usecases.utils.Observer;
import br.edu.ifsp.domain.usecases.utils.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuario implements Observer {
    private String cpf;
    private String email;
    private String senha;
    private String nome;

    private float totalLucrado;
    private float totalInvestido;

    private float lucroPotencial;
    private float valorAtual;
    private float investimentoAtual;

    private List<Grupo> carteira = new ArrayList<>();
    private List<LogGrupo> historico = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String cpf, String nome, String email, String senha, float totalLucrado, float totalInvestido) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
    }

    public Usuario(String cpf, String nome, String email, String senha, Float totalLucrado, Float totalInvestido, Float lucroPotencial, Float valorAtual, Float investimentoAtual) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
        this.lucroPotencial = lucroPotencial;
        this.valorAtual = valorAtual;
        this.investimentoAtual = investimentoAtual;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        grupo.addObserver(this);
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

    public void addLog(LogGrupo logGrupo) {
        this.historico.add(logGrupo);
    }

    public Iterator<LogGrupo> getIteratorHistorico() {
        return this.historico.iterator();
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
    public String toString() {
        return "Usuario{" +
                "cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", totalLucrado=" + totalLucrado +
                ", totalInvestido=" + totalInvestido +
                ", lucroPotencial=" + lucroPotencial +
                ", valorAtual=" + valorAtual +
                ", investimentoAtual=" + investimentoAtual +
                ", carteira=" + carteira +
                ", historico=" + historico +
                '}';
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
