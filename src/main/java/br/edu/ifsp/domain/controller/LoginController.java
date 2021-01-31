package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.inMemory.InMemoryUsuarioDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaCadastrar;
import br.edu.ifsp.domain.ui.JanelaLogin;
import br.edu.ifsp.domain.ui.JanelaPainel;
import br.edu.ifsp.domain.usecases.usuario.CadastroUseCase;
import br.edu.ifsp.domain.usecases.usuario.LoginUseCase;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML public Button btnCriarConta;
    @FXML public TextField inputEmail;
    @FXML public PasswordField inputSenha;
    @FXML public Button btnEntrar;

    private JanelaLogin janelaLogin;
    private UsuarioDAO usuarioDAO;

    public void init(JanelaLogin janelaLogin) {
        this.janelaLogin = janelaLogin;
        this.usuarioDAO = new sqliteUsuarioDAO();
    }

    public void criarConta() {
        JanelaCadastrar janelaCadastrar = new JanelaCadastrar();
        janelaCadastrar.showAndWait();
    }

    private void alertFailLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Não foi possível fazer login.");
        alert.setContentText("Senha ou email não correspondem.");

        alert.showAndWait();
    }

    private void alertExceptionLogin(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Não foi possível fazer login.");
        alert.setContentText(e.getMessage());

        alert.showAndWait();
    }

    private void showPainel(Usuario user) {
        JanelaPainel janelaPainel = new JanelaPainel();
        janelaPainel.show(user);
        janelaLogin.close();
    }

    public void login() {
        String email = inputEmail.getText();
        String senha = inputSenha.getText();

        LoginUseCase loginUseCase = new LoginUseCase(this.usuarioDAO);

        try {
            Usuario logado = loginUseCase.login(email, senha);

            if(logado != null) {
                showPainel(logado);
            } else {
                alertFailLogin();
            }

        } catch(IllegalArgumentException e) {
            alertExceptionLogin(e);
        }
    }
}
