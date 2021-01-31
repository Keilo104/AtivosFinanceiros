package br.edu.ifsp.domain.usecases.ativo.acao;

import br.edu.ifsp.domain.DAOs.AcaoDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class ExcluirAcaoUseCase {
    private AcaoDAO acaoDAO;
    private LogAtivoDAO logAtivoDAO;
    private GrupoDAO grupoDAO;

    public ExcluirAcaoUseCase(AcaoDAO acaoDAO, LogAtivoDAO logAtivoDAO, GrupoDAO grupoDAO) {
        this.acaoDAO = acaoDAO;
        this.logAtivoDAO = logAtivoDAO;
        this.grupoDAO = grupoDAO;
    }

    public boolean delete(Acao acao) {
        Validator<Acao> validator = new AcaoInputValidator();
        Notification notif = validator.validate(acao);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        if(this.grupoDAO.findOneByAtivo(acao).isPresent()) {
            throw new IllegalArgumentException("Cant delete ativo thats in a grupo");
        }

        boolean flag = this.acaoDAO.delete(acao);

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        LogAtivo logAtivo = new LogAtivo(acao, LogAtivoEnum.REMOCAO);
        salvarHistoricoAtivoUseCase.salvarHistorico(logAtivo);

        return flag;
    }
}
