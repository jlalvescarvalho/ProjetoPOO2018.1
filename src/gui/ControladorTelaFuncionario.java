package gui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.Main.*;


public class ControladorTelaFuncionario implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void gerenciarEstoque(ActionEvent actionEvent) {

    }


    public void gerenciarProduto(ActionEvent actionEvent){
        Main.chamarTela("TelaGerenciaProduto.fxml",600,400);
    }

    public void realizarVenda(ActionEvent actionEvent) {
        Main.chamarTela("TelaGerenciaVenda.fxml", 600, 400);
    }
    public void gerenciarCliente(ActionEvent actionEvent){
        Main.chamarTela("TelagerenciaCliente.fxml",600,400);
    }
}

