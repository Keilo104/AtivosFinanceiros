package br.edu.ifsp.domain.entities.relatorio;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;

import java.time.LocalDateTime;

public class RelatorioPeriodo extends Relatorio{
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;

    public RelatorioPeriodo() {
    }

    public RelatorioPeriodo(TipoGrupoEnum categoria) {
        super(categoria);
        this.dataInicial = LocalDateTime.now().minusMonths(1);
        this.dataFinal = LocalDateTime.now();
    }

    public RelatorioPeriodo(TipoGrupoEnum categoria, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        super(categoria);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public RelatorioPeriodo(int id, LocalDateTime dataImpressao, TipoGrupoEnum categoria, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        super(id, dataImpressao, categoria);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public LocalDateTime getDataFinal() {
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
