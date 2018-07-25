package gui.controller;

import fachada.FachadaFuncionario;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.Main.*;

public class ControladorTelaLogin implements Initializable {


    FachadaFuncionario fachadaFuncionario = new FachadaFuncionario();

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }





    public void logar(ActionEvent actionEvent) {

        if(fachadaFuncionario.verificarLogin(login.getText(), senha.getText()) == 1){
            Main.chamarTela("view/TelaGerente.fxml", 600, 400);
        }else if(fachadaFuncionario.verificarLogin(login.getText(), senha.getText()) == 0){
            chamarTela("view/TelaFuncionario.fxml",600,400);
        }else{

        }
    }
}
