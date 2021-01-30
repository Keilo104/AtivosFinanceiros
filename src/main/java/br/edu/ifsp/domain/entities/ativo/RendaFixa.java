package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;

public class RendaFixa extends Ativo{
    private String rendimento;
    private LocalDate dataVencimento;

    public RendaFixa() {
    }

    public RendaFixa(float valor, int quantidade, String rendimento) {
        super(valor, quantidade);
        this.rendimento = rendimento;
    }

    public RendaFixa(float valor, int quantidade, String rendimento, LocalDate dataVencimento) {
        super(valor, quantidade);
        this.rendimento = rendimento;
        this.dataVencimento = dataVencimento;
    }

    public RendaFixa(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDate dataComprado, int quantidade, String rendimento, LocalDate dataVencimento) {
        super(id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade);
        this.rendimento = rendimento;
        this.dataVencimento = dataVencimento;
    }

    public RendaFixa(int idAtivo, String rendimento, String dataVencimento) {
        super(idAtivo);
        this.rendimento = rendimento;
        this.dataVencimento = LocalDate.parse(dataVencimento);

    }

    public String getRendimento() {
        return rendimento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    @Override
    public String getNome() {
        return this.rendimento;
    }

    @Override
    public String toString() {
        return "RendaFixa{" +
                "rendimento='" + rendimento + '\'' +
                ", dataVencimento=" + dataVencimento +
                "} " + super.toString();
    }
}
