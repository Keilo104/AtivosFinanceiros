package br.edu.ifsp.domain.entities.relatorio;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;

import java.time.LocalDate;

public class RelatorioPeriodo extends Relatorio{
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public RelatorioPeriodo() {
    }

    public RelatorioPeriodo(TipoGrupoEnum categoria) {
        super(categoria);
        this.dataInicial = LocalDate.now().minusMonths(1);
        this.dataFinal = LocalDate.now();
    }

    public RelatorioPeriodo(TipoGrupoEnum categoria, LocalDate dataInicial, LocalDate dataFinal) {
        super(categoria);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public RelatorioPeriodo(int id, LocalDate dataImpressao, TipoGrupoEnum categoria, LocalDate dataInicial, LocalDate dataFinal) {
        super(id, dataImpressao, categoria);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    @Override
    public String toString() {
        return "RelatorioPeriodo{" +
                "dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                "} " + super.toString();
    }
}
