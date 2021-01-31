package br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento;

import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.DAOs.AtivosDAO;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class IncluirFundoDeInvestimentoUseCase {
    private FundoDeInvestimentoDAO fundoDeInvestimentoDAO;
    private LogAtivoDAO logAtivoDAO;
    private AtivosDAO ativosDAO;

    public IncluirFundoDeInvestimentoUseCase(AtivosDAO ativosDAO, FundoDeInvestimentoDAO fundoDeInvestimentoDAO, LogAtivoDAO logAtivoDAO) {
        this.fundoDeInvestimentoDAO = fundoDeInvestimentoDAO;
        this.logAtivoDAO = logAtivoDAO;
        this.ativosDAO = ativosDAO;
    }

    public int include(FundoDeInvestimento fundoDeInvestimento) {
        Validator<FundoDeInvestimento> validator = new FundoDeInvestimentoInputValidator();
        Notification notif = validator.validate(fundoDeInvestimento);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        int id = this.ativosDAO.create(fundoDeInvestimento);
        fundoDeInvestimento.setId(id);
        id = this.fundoDeInvestimentoDAO.create(fundoDeInvestimento);

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        LogAtivo logAtivo = new LogAtivo(fundoDeInvestimento, LogAtivoEnum.INCLUSAO);
        salvarHistoricoAtivoUseCase.salvarHistorico(logAtivo);

        return id;
    }
}
