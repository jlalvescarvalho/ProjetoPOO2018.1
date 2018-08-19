package gui.controller;

import fachada.Fachada;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.Venda;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaRelatorioVendas implements Initializable {
    IFachadaGerente gerente = new Fachada();

    private Stage tela;
    @FXML
    private AnchorPane pane;
    @FXML
    private DatePicker dtInicio;
    @FXML
    private DatePicker dtFim;
    @FXML
    private ListView<Venda> lstVendas;
    @FXML
    private ListView<Venda> lstVendasMes;



    public void gerarRelatorioPorDatas(){
        Date dataInicio = Date.valueOf(dtInicio.getValue());
        Date dataFim = Date.valueOf(dtFim.getValue());

        ArrayList<Venda> vendas = gerente.gerarRelatorioPorDatas(dataInicio, dataFim);
        ObservableList observableList = FXCollections.observableArrayList(vendas);
        lstVendas.setItems(observableList);
    }

    public void gerarRelatorioVendasGeral(){
        ArrayList<Venda> vendas = gerente.recuperarTodasVendas();
        ObservableList observableList = FXCollections.observableArrayList(vendas);
        lstVendasMes.setItems(observableList);
    }

    public void cancelar(){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerente.fxml", 600,400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
