package br.edu.ifsp.domain.entities.relatorio;

import br.edu.ifsp.domain.entities.grupo.TipoGrupoEnum;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    private int id;
    private LocalDateTime dataImpressao;
    private TipoGrupoEnum categoria;

    private List<LogTransacaoAtivo> historico = new ArrayList<>();

    public Relatorio() {
        this.dataImpressao = LocalDateTime.now();
    }

    public Relatorio(TipoGrupoEnum categoria) {
        this.dataImpressao = LocalDateTime.now();
        this.categoria = categoria;
    }

    public Relatorio(int id, LocalDateTime dataImpressao, TipoGrupoEnum categoria) {
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

    public LocalDateTime getDataImpressao() {
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
