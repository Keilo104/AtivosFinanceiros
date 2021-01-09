package br.edu.ifsp.domain.entities.log;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.time.LocalDate;

public class LogTransacaoAtivo extends Log {
    private LogTransacaoAtivoEnum tipo;
    private Ativo ativo;
    private float valor;
    private int quantidade;

    public LogTransacaoAtivo() {
    }

    public LogTransacaoAtivo(LogTransacaoAtivoEnum tipo, Ativo ativo, float valor, int quantidade) {
        this.tipo = tipo;
        this.ativo = ativo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public LogTransacaoAtivo(LocalDate data, LogTransacaoAtivoEnum tipo, Ativo ativo, float valor, int quantidade) {
        super(data);
        this.tipo = tipo;
        this.ativo = ativo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public LogTransacaoAtivoEnum getTipo() {
        return tipo;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public float getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "LogTransacaoAtivo{" +
                "tipo=" + tipo +
                ", ativo=" + ativo +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                "} " + super.toString();
    }
}
