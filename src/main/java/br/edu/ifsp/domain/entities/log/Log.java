package br.edu.ifsp.domain.entities.log;

import java.time.LocalDate;

public class Log {
    private LocalDate data;
    private LogEnum tipo;
    private String nomeGrupo;
    private int idAtivo;
    private float valor;
    private int quantidade;

    public Log() {
    }



    public Log(LocalDate data, LogEnum tipo, String nomeGrupo, int idAtivo, float valor, int quantidade) {
        this.data = data;
        this.tipo = tipo;
        this.nomeGrupo = nomeGrupo;
        this.idAtivo = idAtivo;
        this.valor = valor;
        this.quantidade = quantidade;
    }
}
