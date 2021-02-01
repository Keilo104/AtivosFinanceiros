package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RendaFixa extends Ativo {
    private String rendimento;
    private LocalDate dataVencimento;

    public RendaFixa() {
    }

    public RendaFixa( Ativo ativo ) {
        super( ativo.getId(), ativo.getValorUnitarioAtual(), ativo.getValorTotalComprado(), ativo.getValorTotalVendido(), ativo.getDataComprado(), ativo.getQuantidade(), ativo.getIDGrupo() );
    }

    public RendaFixa( float valor, int quantidade, String rendimento ) {
        super( valor, quantidade );
        this.rendimento = rendimento;
    }

    public RendaFixa( float valor, int quantidade, String rendimento, LocalDate dataVencimento ) {
        super( valor, quantidade );
        this.rendimento = rendimento;
        this.dataVencimento = dataVencimento;
    }

    public RendaFixa( int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDateTime dataComprado, int quantidade, String rendimento, LocalDate dataVencimento ) {
        super( id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade );
        this.rendimento = rendimento;
        this.dataVencimento = dataVencimento;
    }

    public RendaFixa( int idAtivo, String rendimento, String dataVencimento ) {
        super( idAtivo );
        this.rendimento = rendimento;
        this.dataVencimento = LocalDate.parse( dataVencimento );
    }

    public String getRendimento() {
        return rendimento;
    }

    public void setRendimento( String rendimento ) {
        this.rendimento = rendimento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento( LocalDate dataVencimento ) {
        this.dataVencimento = dataVencimento;
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
