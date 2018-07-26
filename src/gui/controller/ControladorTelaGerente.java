package gui.controller;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorTelaGerente implements Initializable {

    public void abrirTelaGerenciarFuncionario(ActionEvent actionEvent) {
        Main.chamarTela("view/TelaGerenciaUsuario.fxml",600,400);

    }

    public void abrirTelaRelatoriosVenda(ActionEvent actionEvent) {
        Main.chamarTela("view/RelatorioVendas.fxml",600,400);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
