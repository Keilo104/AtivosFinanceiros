package br.edu.ifsp.domain.usecases.ativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;

public class CompraAtivosUseCase {
    private Ativo ativo;
    private Grupo grupo;

    public CompraAtivosUseCase( Ativo ativo, Grupo grupo ) {
        this.ativo = ativo;
        this.grupo = grupo;
    }

    public CompraAtivosUseCase(AtivosDAO ativosDAO, GrupoDAO grupoDAO) {
    }

    public void compraAtivo() {
        grupo.addAtivo( ativo );
    }
}
