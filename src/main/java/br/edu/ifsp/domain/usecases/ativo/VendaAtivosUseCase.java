package br.edu.ifsp.domain.usecases.ativo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.usecases.grupo.GrupoDAO;

public class VendaAtivosUseCase {
    private Ativo ativo;
    private Grupo grupo;

    public VendaAtivosUseCase( Ativo ativo, Grupo grupo ) {
        this.ativo = ativo;
        this.grupo = grupo;
    }

    public VendaAtivosUseCase(AtivosDAO ativosDAO, GrupoDAO grupoDAO) {
    }

    public void vendaAtivo() {
        grupo.removeAtivo( ativo );
    }
}
