package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.InMemoryUsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;
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
    @FXML private Button btnCriarConta;
    @FXML private TextField inputEmail;
    @FXML private PasswordField inputSenha;
    @FXML private Button btnEntrar;

    public LoginController() {
    }

    public void criarConta() {
        UsuarioDAO usuarioDAO = new InMemoryUsuarioDAO();
        Usuario usuario = new Usuario("1234", "email_legal", "123");

        CadastroUseCase cadastroUseCase = new CadastroUseCase(usuarioDAO);
        cadastroUseCase.cadastrar(usuario);
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
    }

    public void login() {
        UsuarioDAO usuarioDAO = new InMemoryUsuarioDAO();

        String email = inputEmail.getText();
        String senha = inputSenha.getText();

        LoginUseCase loginUseCase = new LoginUseCase(usuarioDAO);

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
