package gui.controller;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;



public class ControladorTelaFuncionario implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void gerenciarEstoque(ActionEvent actionEvent) {
        Main.chamarTela("view/TelaGerenciaEstoque.fxml",600,400);
    }


    public void gerenciarProduto(ActionEvent actionEvent){
        Main.chamarTela("view/TelaGerenciaProduto.fxml",600,400);
    }

    public void realizarVenda(ActionEvent actionEvent) {
        Main.chamarTela("view/TelaGerenciaVenda.fxml", 600, 400);
    }
    public void gerenciarCliente(ActionEvent actionEvent){
        Main.chamarTela("view/TelagerenciaCliente.fxml",600,400);
    }
}

