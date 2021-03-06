package br.edu.ifsp.domain.entities.ativo;

import java.time.LocalDateTime;

public class Acao extends Ativo {
    private String codigo;
    private String nome;
    private String pais;

    public Acao() {
    }

    public Acao( Ativo ativo ) {
        super( ativo.getId(), ativo.getValorUnitarioAtual(), ativo.getValorTotalComprado(), ativo.getValorTotalVendido(), ativo.getDataComprado(), ativo.getQuantidade(), ativo.getIDGrupo() );
    }

    public Acao( float valorUnitarioAtual, int quantidade, String nome, String codigo, String pais ) {
        super( valorUnitarioAtual, quantidade );
        this.nome = nome;
        this.codigo = codigo;
        this.pais = pais;
    }

    public Acao( int id, float valorUnitarioAtual, float valorUnitarioComprado, float valorTotalVendido, LocalDateTime dataComprado, int quantidade, String codigo, String pais ) {
        super( id, valorUnitarioAtual, valorUnitarioComprado, valorTotalVendido, dataComprado, quantidade );
        this.codigo = codigo;
        this.pais = pais;
    }

    public Acao( String codigo, String pais, String nome ) {
        this.codigo = codigo;
        this.nome = nome;
        this.pais = pais;
    }

    public Acao( float valorUnitarioAtual ) {
        super( valorUnitarioAtual );
    }

    public Acao( int idAtivo, String codigo, String pais, String nome ) {
        super( idAtivo );
        this.codigo = codigo;
        this.pais = pais;
    }

    public void updateFromAPI( float novoValor ) {
        this.setValorUnitarioAtual( novoValor );
        notifyObservers();
    }

    public String getPais() {
        return pais;
    }

    public void setPais( String pais ) {
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo( String codigo ) {
        this.codigo = codigo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Acao{" +
                "codigo='" + codigo + '\'' +
                ", pais='" + pais + '\'' +
                '}' + super.toString();
    }
}
