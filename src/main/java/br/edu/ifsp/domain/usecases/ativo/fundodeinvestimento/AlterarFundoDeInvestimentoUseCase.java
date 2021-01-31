package br.edu.ifsp.domain.usecases.ativo.fundodeinvestimento;

import br.edu.ifsp.domain.DAOs.FundoDeInvestimentoDAO;
import br.edu.ifsp.domain.entities.ativo.FundoDeInvestimento;
import br.edu.ifsp.domain.entities.log.LogAtivo;
import br.edu.ifsp.domain.entities.log.LogAtivoEnum;
import br.edu.ifsp.domain.DAOs.LogAtivoDAO;
import br.edu.ifsp.domain.usecases.log.logativo.SalvarHistoricoAtivoUseCase;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarFundoDeInvestimentoUseCase {
    private FundoDeInvestimentoDAO fundoDeInvestimentoDAO;
    private LogAtivoDAO logAtivoDAO;

    public AlterarFundoDeInvestimentoUseCase(FundoDeInvestimentoDAO fundoDeInvestimentoDAO, LogAtivoDAO logAtivoDAO) {
        this.fundoDeInvestimentoDAO = fundoDeInvestimentoDAO;
        this.logAtivoDAO = logAtivoDAO;
    }

    public boolean update(FundoDeInvestimento fundoDeInvestimento) {
        Validator<FundoDeInvestimento> validator = new FundoDeInvestimentoInputValidator();
        Notification notif = validator.validate(fundoDeInvestimento);

        if(notif.hasErrors()) {
            throw new IllegalArgumentException(notif.errorMessage());
        }

        boolean flag = this.fundoDeInvestimentoDAO.update(fundoDeInvestimento);

        SalvarHistoricoAtivoUseCase salvarHistoricoAtivoUseCase = new SalvarHistoricoAtivoUseCase(logAtivoDAO);
        LogAtivo logAtivo = new LogAtivo(fundoDeInvestimento, LogAtivoEnum.ALTERACAO);
        salvarHistoricoAtivoUseCase.salvarHistorico(logAtivo);

        return flag;
    }
}
