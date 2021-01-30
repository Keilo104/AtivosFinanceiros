package br.edu.ifsp.domain.entities.relatorio;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    private int id;
    private LocalDate dataImpressao;
    private TipoGrupoEnum categoria;

    private List<LogTransacaoAtivo> historico = new ArrayList<>();

    public Relatorio() {
        this.dataImpressao = LocalDate.now();
    }

    public Relatorio(TipoGrupoEnum categoria) {
        this.dataImpressao = LocalDate.now();
        this.categoria = categoria;
    }

    public Relatorio(int id, LocalDate dataImpressao, TipoGrupoEnum categoria) {
        this.id = id;
        this.dataImpressao = dataImpressao;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataImpressao() {
        return dataImpressao;
    }

    public TipoGrupoEnum getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "id=" + id +
                ", dataImpressao=" + dataImpressao +
                ", categoria=" + categoria +
                '}';
    }
}
