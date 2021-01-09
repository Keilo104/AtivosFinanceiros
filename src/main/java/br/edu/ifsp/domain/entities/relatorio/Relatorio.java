package br.edu.ifsp.domain.entities.relatorio;

import java.time.LocalDate;

public class Relatorio {
    private LocalDate dataImpressao;
    private CategoriaEnum categoria;

    public Relatorio() {
        this.dataImpressao = LocalDate.now();
    }

    public Relatorio(CategoriaEnum categoria) {
        this.dataImpressao = LocalDate.now();
        this.categoria = categoria;
    }

    public Relatorio(LocalDate dataImpressao, CategoriaEnum categoria) {
        this.dataImpressao = dataImpressao;
        this.categoria = categoria;
    }

    public LocalDate getDataImpressao() {
        return dataImpressao;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }


}
