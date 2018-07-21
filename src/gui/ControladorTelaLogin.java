package gui;

import fachada.FachadaFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.Main.*;

public class ControladorTelaLogin implements Initializable {

    FachadaFuncionario fachadaFuncionario = new FachadaFuncionario();
    @FXML
    private Stage tela;

    @FXML
    private Pane pane;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtSenha;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }





    public void logar(ActionEvent actionEvent) {

        if(fachadaFuncionario.verificarLogin(txtUsuario.getText(), txtSenha.getText()) == 1){
            Main.chamarTela("TelaGerente.fxml", 600, 400);
        }else if(fachadaFuncionario.verificarLogin(txtUsuario.getText(), txtSenha.getText()) == 0){
            chamarTela("TelaFuncionario.fxml",600,400);
        }else{

        }
    }
}
