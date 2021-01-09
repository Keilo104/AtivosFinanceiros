package br.edu.ifsp.domain.entities.relatorio;

import java.time.LocalDate;

public class RelatorioPeriodo extends Relatorio {
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public RelatorioPeriodo() {
    }

    public RelatorioPeriodo(CategoriaEnum categoria) {
        super(categoria);
        this.dataInicial = LocalDate.now().minusMonths(1);
        this.dataFinal = LocalDate.now();
    }

    public RelatorioPeriodo(CategoriaEnum categoria, LocalDate dataInicial, LocalDate dataFinal) {
        super(categoria);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public RelatorioPeriodo(LocalDate dataImpressao, CategoriaEnum categoria, LocalDate dataInicial, LocalDate dataFinal) {
        super(dataImpressao, categoria);
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
