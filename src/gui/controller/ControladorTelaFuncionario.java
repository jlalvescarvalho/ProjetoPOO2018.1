package gui.controller;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class ControladorTelaFuncionario implements Initializable {

    private Stage tela;
    @FXML
    private Pane pane;



    public void gerenciarEstoque(ActionEvent actionEvent) {
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerenciaEstoque.fxml",600,400);
    }


    public void gerenciarProduto(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerenciaProduto.fxml",600,400);
    }

    public void realizarVenda(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerenciaVenda.fxml", 600, 400);
    }

    public void gerenciarCliente(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerenciaCliente.fxml",600,400);
    }


    public void deslogar(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaLogin.fxml",600, 400);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

