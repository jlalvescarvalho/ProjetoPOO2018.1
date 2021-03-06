package gui.controller;

import execoes.CPFInvalidoException;
import execoes.CPFTamanhoException;
import execoes.UsuarioInvalidoException;
import execoes.UsuarioNaoExisteException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.Main.*;

public class ControladorTelaLogin implements Initializable {
    Fachada fachada = new Fachada();


    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    private Stage tela;

    @FXML
    private Pane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setPromptText("Cpf");
        senha.setPromptText("Senha");
    }





    public void logar() {

        try {
            if(fachada.verificarLogin(login.getText(), senha.getText()) == 1){

                Main.chamarTela("view/TelaGerente.fxml", 600, 400);
                tela = (Stage) this.pane.getScene().getWindow();
                tela.close();
            }else if(fachada.verificarLogin(login.getText(), senha.getText()) == 0){
                chamarTela("view/TelaFuncionario.fxml",600,400);
                tela = (Stage) this.pane.getScene().getWindow();
                tela.close();
            }
        } catch (UsuarioInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CPFInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CPFTamanhoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }
}
