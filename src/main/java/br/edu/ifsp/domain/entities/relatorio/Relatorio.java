package br.edu.ifsp.domain.entities.relatorio;

import java.time.LocalDate;

public class Relatorio {
    private int id;
    private LocalDate dataImpressao;
    private CategoriaEnum categoria;

    public Relatorio() {
        this.dataImpressao = LocalDate.now();
    }

    public Relatorio(CategoriaEnum categoria) {
        this.dataImpressao = LocalDate.now();
        this.categoria = categoria;
    }

    public Relatorio(int id, LocalDate dataImpressao, CategoriaEnum categoria) {
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

    public CategoriaEnum getCategoria() {
        return categoria;
    }


}
