package br.edu.ifsp.domain.entities.ativo;

import br.edu.ifsp.domain.usecases.utils.Subject;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ativo extends Subject {
    private int id;

    private float valorUnitarioAtual;
    private float valorUnitarioComprado;
    private float valorTotalVendido;

    private LocalDateTime dataComprado;
    private int quantidade;

    public int IDGrupo;

    public Ativo() {
        this.dataComprado = LocalDateTime.now();
    }

    public Ativo(float valorUnitarioAtual, int quantidade) {
        this(0, valorUnitarioAtual, valorUnitarioAtual, 0, LocalDateTime.now(), quantidade);
    }

    public Ativo(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDateTime dataComprado, int quantidade) {
        this.id = id;
        this.valorUnitarioAtual = valorUnitarioAtual;
        this.valorUnitarioComprado = valorUnitarioComprado;
        this.valorTotalVendido = valorTotalVendido;
        this.dataComprado = dataComprado;
        this.quantidade = quantidade;
    }

    public Ativo(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDateTime dataComprado, int quantidade, int grupoId) {
        this.id = id;
        this.valorUnitarioAtual = valorUnitarioAtual;
        this.valorUnitarioComprado = valorUnitarioComprado;
        this.valorTotalVendido = valorTotalVendido;
        this.dataComprado = dataComprado;
        this.quantidade = quantidade;
        this.IDGrupo = grupoId;
    }

    public Ativo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorTotalAtual() {
        return this.valorUnitarioAtual * this.quantidade;
    }

    public float getValorTotalComprado() {
        return this.valorUnitarioComprado * this.quantidade;
    }

    public float getValorUnitarioComprado() {
        return this.valorUnitarioComprado;
    }

    public float getValorTotalVendido() {
        return this.valorTotalVendido;
    }

    public float getValorUnitarioAtual() {
        return valorUnitarioAtual;
    }

    public void setValorUnitarioAtual(float valorUnitarioAtual) {
        this.valorUnitarioAtual = valorUnitarioAtual;
        notifyObservers();
    }

    public LocalDateTime getDataComprado() {
        return dataComprado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getIDGrupo() {
        return IDGrupo;
    }

    public void setIDGrupo(int IDGrupo) {
        this.IDGrupo = IDGrupo;
    }

    public void vender(int quantidade) {
        if(quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            notifyObservers();
        } else {
            if(quantidade < 0) {
                throw new InvalidQuantityToVenderException("Cannot vender 0 or less ativos");
            } else {
                throw new InvalidQuantityToVenderException("Cannot vender more ativos than you have");
            }
        }
    }

    public String getNome() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ativo ativo = (Ativo) o;
        return id == ativo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", valorUnitarioAtual=" + valorUnitarioAtual +
                ", valorUnitarioComprado=" + valorUnitarioComprado +
                ", valorTotalVendido=" + valorTotalVendido +
                ", dataComprado=" + dataComprado +
                ", quantidade=" + quantidade +
                "} " + super.toString();
    }
}
