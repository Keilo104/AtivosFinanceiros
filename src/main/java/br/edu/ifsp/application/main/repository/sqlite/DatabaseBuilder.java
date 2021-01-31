package br.edu.ifsp.application.main.repository.sqlite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public static void buildDatabaseIfMissing(){
        if(isDatabaseMissing()){
            buildTables();
        }
    }

    public static void dropAll() {
        try {
            Files.delete(Paths.get("database.db"));

            System.out.println("Base de dados genocidada com sucesso");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean isDatabaseMissing(){
        return Files.notExists(Paths.get("database.db"));
    }

    private static void buildTables(){
        try(Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createUsuarioTable());
            statement.addBatch(createGrupoTable());
            statement.addBatch(createAtivoTable());
            statement.addBatch(createAcaoTable());
            statement.addBatch(createFundoDeInvestimentoTable());
            statement.addBatch(createRendaFixaTable());
            statement.addBatch(createLogAtivoTable());
            statement.addBatch(createLogTransacaoAtivoTable());
            statement.addBatch(createLogGrupoTable());
            statement.addBatch(createTokenTable());
            statement.addBatch(createRelatorioTable());
            statement.addBatch(createRelatorioPeriodoTable());
            statement.executeBatch();

            System.out.println("Base de dados criada com sucesso");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String createAtivoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ATIVO(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("valorUnitarioAtual REAL NOT NULL, \n");
        builder.append("valorUnitarioComprado REAL,\n");
        builder.append("valorTotalVendido REAL,\n");
        builder.append("dataComprado TEXT,\n");
        builder.append("quantidade INTEGER,\n");
        builder.append("grupoId INTEGER,\n");
        builder.append("FOREIGN KEY (grupoId) REFERENCES GRUPO(id)");
        builder.append("); \n");
        return builder.toString();
    }

    private static String createAcaoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ACAO(");
        builder.append("idAtivo INTEGER PRIMARY KEY,\n");
        builder.append("codigo TEXT,\n");
        builder.append("nome TEXT, \n");
        builder.append("pais INTEGER,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id)");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createFundoDeInvestimentoTable(){
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

    private static String createRendaFixaTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE RENDA_FIXA(");
        builder.append("idAtivo INTEGER PRIMARY KEY,\n");
        builder.append("rendimento TEXT,\n");
        builder.append("dataVencimento TEXT,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id)");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createGrupoTable(){
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
        builder.append("cpfUsuario TEXT NOT NULL,\n");
        builder.append("FOREIGN KEY (cpfUsuario) REFERENCES USUARIO(cpf)");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createUsuarioTable(){
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
        builder.append("investimentoAtual REAL\n");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createLogAtivoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG_ATIVO(");
        builder.append("idAtivo INTEGER, \n");
        builder.append("data TEXT,\n");
        builder.append("tipo TEXT,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id),");
        builder.append("PRIMARY KEY(idAtivo, data)\n");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createLogGrupoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG_GRUPO(");
        builder.append("idGrupo INTEGER, \n");
        builder.append("data TEXT,\n");
        builder.append("valorTotal REAL,\n");
        builder.append("mudanca REAL,\n");
        builder.append("FOREIGN KEY (idGrupo) REFERENCES GRUPO(id),");
        builder.append("PRIMARY KEY(idGrupo, data)\n");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createLogTransacaoAtivoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE LOG_TRANSACAO_ATIVO(");
        builder.append("idAtivo INTEGER, \n");
        builder.append("data TEXT,\n");
        builder.append("tipo TEXT,\n");
        builder.append("valor REAL,\n");
        builder.append("quantidade INTEGER,\n");
        builder.append("FOREIGN KEY (idAtivo) REFERENCES ATIVO(id),");
        builder.append("PRIMARY KEY(idAtivo, data)\n");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createTokenTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE TOKEN(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("data TEXT,\n");
        builder.append("cpfUsuario TEXT,\n");
        builder.append("token TEXT,\n");
        builder.append("FOREIGN KEY (cpfUsuario) REFERENCES USUARIO(cpf)");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createRelatorioTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE RELATORIO(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("dataImpressao TEXT,\n");
        builder.append("categoria TEXT\n");
        builder.append("); \n");

        return builder.toString();
    }

    private static String createRelatorioPeriodoTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE RELATORIO_PERIODO(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("categoria TEXT,\n");
        builder.append("dataImpressao TEXT,\n");
        builder.append("dataInicial TEXT,\n");
        builder.append("dataFinal TEXT\n");
        builder.append("); \n");

        return builder.toString();
    }
}
