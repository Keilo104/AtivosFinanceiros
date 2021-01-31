package br.edu.ifsp.domain.entities.log;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class LogAtivo extends Log{
    private Ativo ativo;
    private LogAtivoEnum tipo;

    public LogAtivo() {
    }

    public LogAtivo(Ativo ativo, LogAtivoEnum tipo) {
        this.ativo = ativo;
        this.tipo = tipo;
    }

    public LogAtivo(LocalDateTime data, Ativo ativo, LogAtivoEnum tipo) {
        super(data);
        this.ativo = ativo;
        this.tipo = tipo;
    }

    public Pair<LocalDateTime, Ativo> generateKey() {
        return new Pair<>(this.getData(), this.getAtivo());
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public LogAtivoEnum getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "LogAtivo{" +
                "ativo=" + ativo +
                ", tipo=" + tipo +
                "} " + super.toString();
    }
}
