package br.edu.ifsp.domain.entities.usuario;

import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.util.Iterator;
import java.util.List;

public class Usuario {
    private String email;
    private String senha;
    private float lucro;
    private float investido;

    private List<Grupo> carteira;

    public Usuario() {

    }

    public Usuario(String senha, String email) {
        this(senha, email, 0, 0);
    }

    public Usuario(String senha, String email, float lucro, float investido) {
        this.senha = senha;
        this.email = email;
        this.lucro = lucro;
        this.investido = investido;
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

    public float getLucro() {
        return lucro;
    }

    public void setLucro(float lucro) {
        this.lucro = lucro;
    }

    public float getInvestido() {
        return investido;
    }

    public void setInvestido(float investido) {
        this.investido = investido;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", carteira=" + carteira +
                ", lucro=" + lucro +
                ", investido=" + investido +
                '}';
    }
}
