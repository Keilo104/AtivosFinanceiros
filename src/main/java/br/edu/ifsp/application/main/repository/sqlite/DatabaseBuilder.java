package br.edu.ifsp.application.main.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildDatabaseIfMissing(){
        if(isDatabaseMissing()){
            buildTables();
        }
    }

    private boolean isDatabaseMissing(){
        return !Files.exists(Paths.get("databse.db"));
    }

    private void buildTables(){
        try(Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createUsuarioTable());
            statement.addBatch(createGrupoTable());
            statement.addBatch(createAtivoTable());
            statement.addBatch(createAcaoTable());
            statement.addBatch(createFundoDeInvestimentoTable());
            statement.addBatch(createRendaFixaTable());
            statement.addBatch(createLogTable());
            statement.addBatch(createLogAtivoTable());
            statement.addBatch(createLogTransacaoAtivoTable());
            statement.addBatch(createLogGrupoTable());
            statement.executeBatch();

            System.out.println("Base de dados criada com sucesso");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createAtivoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ATIVO(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("valorUnitarioAtual REAL NOT NULL, \n");
        builder.append("valorUnitarioComprado REAL,\n");
        builder.append("valorTotalVendido REAL,\n");
        builder.append("dataComprado TEXT,\n");
        builder.append("quantidade INTEGER,\n");
        builder.append("); \n");
        return builder.toString();
    }

    private String createAcaoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ACAO(");
        builder.append("idAtivo INTEGER PRIMARY KEY,\n");
        builder.append("codigo TEXT,\n");
        builder.append("pais INTEGER,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id)");
        builder.append("); \n");

        return builder.toString();
    }

    private String createFundoDeInvestimentoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE FUNDO_DE_INVESTIMENTO(");
        builder.append("idAtivo INTEGER PRIMARY KEY,\n");
        builder.append("nome TEXT,\n");
        builder.append("rentabilidade TEXT,\n");
        builder.append("taxaAdministrativa REAL,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id)");
        builder.append("); \n");

        return builder.toString();
    }

    private String createRendaFixaTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE RENDA_FIXA(");
        builder.append("idAtivo INTEGER PRIMARY KEY,\n");
        builder.append("rendimento TEXT,\n");
        builder.append("dataVencimento TEXT,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id)");
        builder.append("); \n");

        return builder.toString();
    }

    private String createGrupoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE GRUPO(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("nome TEXT,\n");
        builder.append("totalLucrado REAL, \n");
        builder.append("totalInvestido REAL,\n");
        builder.append("lucroPotencial REAL,\n");
        builder.append("valorAtual REAL,\n");
        builder.append("investimentoAtual REAL,\n");
        builder.append("tipoGrupo TEXT,\n");
        builder.append("cpfUsuario TEXT,\n");
        builder.append("FOREIGN KEY (cpfUsuario) REFERENCES USUARIO(cpf)");
        builder.append("); \n");

        return builder.toString();
    }

    private String createUsuarioTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE USUARIO(");
        builder.append("cpf TEXT PRIMARY KEY,\n");
        builder.append("nome TEXT,\n");
        builder.append("email TEXT,\n");
        builder.append("senha TEXT,\n");
        builder.append("totalLucrado REAL, \n");
        builder.append("totalInvestido REAL,\n");
        builder.append("lucroPotencial REAL,\n");
        builder.append("valorAtual REAL,\n");
        builder.append("investimentoAtual REAL,\n");
        builder.append("); \n");

        return builder.toString();
    }

    private String createLogTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG(");

        return builder.toString();
    }

    private String createLogAtivoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG_ATIVO(");

        return builder.toString();
    }

    private String createLogGrupoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG_GRUPO(");

        return builder.toString();
    }

    private String createLogTransacaoAtivoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG_TRANSACAO_ATIVO(");

        return builder.toString();
    }


}
