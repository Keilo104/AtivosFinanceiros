package br.edu.ifsp.domain.entities.log;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class LogTransacaoAtivo extends Log {
    private LogTransacaoAtivoEnum tipo;
    private Ativo ativo;
    private float valor;
    private int quantidade;

    public LogTransacaoAtivo() {
    }

    public LogTransacaoAtivo( Ativo ativo, LogTransacaoAtivoEnum tipo, float valor, int quantidade ) {
        this.ativo = ativo;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public LogTransacaoAtivo( LocalDateTime data, LogTransacaoAtivoEnum tipo, Ativo ativo, float valor, int quantidade ) {
        super( data );
        this.tipo = tipo;
        this.ativo = ativo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Pair<LocalDateTime, Ativo> generateKey() {
        return new Pair<>( this.getData(), this.getAtivo() );
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
