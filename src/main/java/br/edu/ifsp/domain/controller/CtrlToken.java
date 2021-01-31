package br.edu.ifsp.domain.controller;

import br.edu.ifsp.application.main.repository.sqlite.sqliteTokenDAO;
import br.edu.ifsp.application.main.repository.sqlite.sqliteUsuarioDAO;
import br.edu.ifsp.domain.DAOs.TokenDAO;
import br.edu.ifsp.domain.DAOs.UsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.ui.JanelaLogin;
import br.edu.ifsp.domain.ui.JanelaRecuperar;
import br.edu.ifsp.domain.usecases.usuario.RecuperarSenhaUseCase;
import com.sun.javafx.binding.StringFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class CtrlToken {
    @FXML public AnchorPane tokenPane;
    @FXML public AnchorPane passwordPane;
    @FXML public TextField txtFieldToken;
    @FXML public Label labelSenhaInvalida;
    @FXML public Label labelSenhaIgual;
    @FXML public Label labelSenha;
    @FXML public Label labelConfirmar;
    @FXML public Label labelToken;
    @FXML public Label labelCpfInvalido;
    @FXML public PasswordField txtFieldSenha;
    @FXML public PasswordField txtFieldConfirmarSenha;
    @FXML public Label labelTokenInvalido;

    @FXML public Button btnEnviar;
    @FXML public TextField txtFieldCpf;
    @FXML public Button btnCheckToken;
    @FXML public Button btnContinuar;

    private JanelaRecuperar janelaRecuperar;
    private static UsuarioDAO usuarioDAO;
    private static TokenDAO tokenDAO;
    private static RecuperarSenhaUseCase recuperarSenhaUseCase;

    public void init(JanelaRecuperar janelaRecuperar) {
        this.janelaRecuperar = janelaRecuperar;
        this.usuarioDAO = new sqliteUsuarioDAO();
        this.tokenDAO = new sqliteTokenDAO();
        recuperarSenhaUseCase = new RecuperarSenhaUseCase(usuarioDAO, tokenDAO);
        tokenPane.setVisible(false);
        passwordPane.setVisible(false);
        labelCpfInvalido.setVisible(false);
    }

    public void enviarToken(){
        String cpf = txtFieldCpf.getText();
        if(!cpf.isEmpty()) {
            btnEnviar.setDisable(true);
            if(recuperarSenhaUseCase.enviarToken(cpf)) {
                tokenPane.setVisible(true);
                labelTokenInvalido.setVisible(false);
                labelSenhaInvalida.setVisible(false);
                labelSenhaIgual.setVisible(false);
                txtFieldCpf.setDisable(true);
                btnEnviar.setDisable(true);
                labelCpfInvalido.setVisible(false);
            }
            else{
                labelCpfInvalido.setVisible(true);
                btnEnviar.setDisable(false);
            }
        }
    }

    public void checkToken(){
        String token = txtFieldToken.getText();
        if(recuperarSenhaUseCase.verificarToken(token)){
            passwordPane.setVisible(true);
        }
        else{
            labelTokenInvalido.setVisible(true);
        }
    }

    public void changePassword( ActionEvent actionEvent ) {
        String senha = txtFieldSenha.getText();
        String confirma = txtFieldConfirmarSenha.getText();

        if(!senha.isEmpty() && !confirma.isEmpty() && senha.equals(confirma)){
            String cpf = txtFieldCpf.getText();
            Optional<Usuario> u = usuarioDAO.findOne(cpf);
            if(!u.isEmpty()) {
                Usuario usuario = u.get();
                String senhaAntiga = usuario.getSenha();
                if (!senha.equals(senhaAntiga)) {
                    usuario.setSenha(senha);
                    usuarioDAO.update(usuario);
                    passwordChangeSuccess();
                    janelaRecuperar.close();
                }else{
                    labelSenhaIgual.setVisible(true);
                }
            }
        }else{
            labelSenhaInvalida.setVisible(true);
        }
    }
    private void passwordChangeSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Senha trocada com sucesso");
        alert.setHeaderText("Senha trocada com sucesso.");
        alert.setContentText("Senha trocada com sucesso.");

        alert.showAndWait();
    }
}
