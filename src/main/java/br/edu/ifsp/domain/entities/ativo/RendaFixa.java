package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDateTime;

public class RendaFixa extends Ativo{
    private String rendimento;
    private LocalDateTime dataVencimento;

    public RendaFixa() {
    }

    public RendaFixa(float valor, int quantidade, String rendimento) {
        super(valor, quantidade);
        this.rendimento = rendimento;
    }

    public RendaFixa(float valor, int quantidade, String rendimento, LocalDateTime dataVencimento) {
        super(valor, quantidade);
        this.rendimento = rendimento;
        this.dataVencimento = dataVencimento;
    }

    public RendaFixa(int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDateTime dataComprado, int quantidade, String rendimento, LocalDateTime dataVencimento) {
        super(id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade);
        this.rendimento = rendimento;
        this.dataVencimento = dataVencimento;
    }

    public RendaFixa(int idAtivo, String rendimento, String dataVencimento) {
        super(idAtivo);
        this.rendimento = rendimento;
        this.dataVencimento = LocalDateTime.parse(dataVencimento);

    }

    public String getRendimento() {
        return rendimento;
    }

    public LocalDateTime getDataVencimento() {
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
