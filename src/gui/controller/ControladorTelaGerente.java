package gui.controller;

import fachada.Fachada;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorTelaGerente implements Initializable {

    private Stage tela;
    @FXML
    private Pane pane;
    Fachada fachada = new Fachada();


    public void abrirTelaGerenciarFuncionario(){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerenciaUsuario.fxml",600,400);

    }

    public void abrirTelaRelatoriosVenda() {
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/RelatorioVendas.fxml",600,400);
    }

    public void deslogar(){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        fachada.logoff();
        Main.chamarTela("view/TelaLogin.fxml", 600,400);
    }

    public void abrirTelaPromover() {
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaPromover.fxml",600,400);
    }

    public void abrirTelaComprasPorCliente() {
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaCompraPorCliente.fxml",600,400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
