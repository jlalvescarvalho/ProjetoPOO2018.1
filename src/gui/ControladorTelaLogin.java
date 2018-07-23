package gui;

import fachada.FachadaFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.Main.*;

public class ControladorTelaLogin implements Initializable {

    FachadaFuncionario fachadaFuncionario = new FachadaFuncionario();

    @FXML
    private TextArea login;

    @FXML
    private PasswordField senha;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }





    public void logar(ActionEvent actionEvent) {

        if(fachadaFuncionario.verificarLogin(login.getText(), senha.getText()) == 1){
            Main.chamarTela("telaGerente.fxml", 600, 400);
        }else if(fachadaFuncionario.verificarLogin(login.getText(), senha.getText()) == 0){
            chamarTela("TelaFuncionario.fxml",600,400);
        }else{

        }
    }
}
