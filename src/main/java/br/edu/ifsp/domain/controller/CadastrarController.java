package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.inMemory.InMemoryUsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaCadastrar;
import br.edu.ifsp.domain.usecases.usuario.CadastroUseCase;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.awt.Desktop;
import java.net.URI;

public class CadastrarController {
    @FXML public TextField textFieldCPF;
    @FXML public TextField textFieldNome;
    @FXML public TextField textFieldLogin;
    @FXML public PasswordField passwordFieldSenha;

    JanelaCadastrar janelaCadastrar;

    public void init(JanelaCadastrar janelaCadastrar) {
        this.janelaCadastrar = janelaCadastrar;
    }

    public void cadastrar() {
        String cpf = textFieldCPF.getText();
        String nome = textFieldNome.getText();
        String login = textFieldLogin.getText();
        String senha = passwordFieldSenha.getText();

        UsuarioDAO usuarioDAO = new InMemoryUsuarioDAO();
        CadastroUseCase cadastroUseCase = new CadastroUseCase(usuarioDAO);

        Usuario user = new Usuario(cpf, nome, login, senha);

        try {
            if(cadastroUseCase.cadastrar(user) != null) {
                alertSuccessCadastro();
                janelaCadastrar.close();
            } else {
                alertFailCadastro();
            }
        } catch(IllegalArgumentException e) {
            alertException(e);
        }
    }

    private void alertException(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de cadastro");
        alert.setHeaderText("Não foi possível fazer cadastro.");
        alert.setContentText(e.getMessage());

        alert.showAndWait();
    }

    private void alertFailCadastro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Não foi possível fazer cadastro.");
        alert.setContentText("Não foi possível cadastrar esse usuário, CPF já cadastrado.");

        alert.showAndWait();
    }

    private void alertSuccessCadastro() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro realizado com sucesso!");
        alert.setHeaderText("Cadastro realizado com sucesso!");
        alert.setContentText("Cadastro realizado com sucesso! :)");

        alert.showAndWait();
    }

    public void rickroll() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            }
        } catch (Exception ignored) {

        }
    }
}
