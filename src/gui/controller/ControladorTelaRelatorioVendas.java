package gui.controller;

import fachada.Fachada;
import fachada.IFachadaGerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import negocio.entidade.Venda;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaRelatorioVendas implements Initializable {
    IFachadaGerente gerente = new Fachada();

    @FXML
    private DatePicker dtInicio;
    @FXML
    private DatePicker dtFim;
    @FXML
    private ListView<Venda> lstVendas;



    public void gerarRelatorioPorDatas(){
        Date dataInicio = Date.valueOf(dtInicio.getValue());
        Date dataFim = Date.valueOf(dtFim.getValue());

        ArrayList<Venda> vendas = gerente.gerarRelatorioPorDatas(dataInicio, dataFim);
        ObservableList observableList = FXCollections.observableArrayList(vendas);
        lstVendas.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
