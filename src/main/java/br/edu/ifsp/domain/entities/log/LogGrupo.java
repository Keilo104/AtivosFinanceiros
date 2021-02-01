package br.edu.ifsp.domain.entities.log;

import br.edu.ifsp.domain.entities.grupo.Grupo;

import java.time.LocalDateTime;

public class LogGrupo extends Log {
    private Grupo grupo;
    private float valorTotal;
    private float mudanca;

    public LogGrupo() {
    }

    public LogGrupo( Grupo grupo, float valorTotal, float mudanca ) {
        this.grupo = grupo;
        this.valorTotal = valorTotal;
        this.mudanca = mudanca;
    }

    public LogGrupo( LocalDateTime data, Grupo grupo, float valorTotal, float mudanca ) {
        super( data );
        this.grupo = grupo;
        this.valorTotal = valorTotal;
        this.mudanca = mudanca;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public float getMudanca() {
        return mudanca;
    }

    @Override
    public String toString() {
        return "LogGrupo{" +
                "grupo=" + grupo +
                ", valorTotal=" + valorTotal +
                ", mudanca=" + mudanca +
                "} " + super.toString();
    }
}
