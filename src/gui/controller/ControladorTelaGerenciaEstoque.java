package gui.controller;

import execoes.ApenasNumerosException;
import execoes.CodigoInvalidoException;
import execoes.ProdutoNaoExisteException;
import execoes.QuantidadeInvalidaException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.ItemEstoque;
import negocio.entidade.Produto;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaEstoque implements Initializable {

    IFachadaFuncionario funcionario = new Fachada();

    private Stage tela;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtCodigoEntrada;
    @FXML
    private TextField txtQuantidadeEntrada;
    @FXML
    private ListView<ItemEstoque> lstEstoque;
    @FXML
    private TextField txtCodigoRemover;


    public void realizarEntradaEmEstoque(){

        try {
            verificarEntradas("Codigo", txtCodigoEntrada.getText());
            verificarEntradas("Quantidade", txtQuantidadeEntrada.getText());

            funcionario.realizarEntradaEstoque(txtCodigoEntrada.getText() , Integer.parseInt(txtQuantidadeEntrada.getText()));

            txtCodigoEntrada.setText("");
            txtQuantidadeEntrada.setText("");

        } catch (QuantidadeInvalidaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CodigoInvalidoException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ProdutoNaoExisteException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ApenasNumerosException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }


    }

    private void verificarEntradas(String campoText, String txt) throws CodigoInvalidoException, ApenasNumerosException {
        char[] codigoChar = txt.toCharArray();
        if (txt.equals("") || txt.equals(" ")){
            throw new CodigoInvalidoException();
        }else {
            for (int i = 0; i < codigoChar.length; i++) {
                if (!Character.isDigit(codigoChar[i])) {
                    throw new ApenasNumerosException(campoText);
                }
            }
        }
    }

    public void listarEstoque(){
        ArrayList<ItemEstoque> estoque = funcionario.recuperarEstoque();
        ObservableList observableList = FXCollections.observableArrayList(estoque);
        lstEstoque.setItems(observableList);
    }

    public void removerItemEstoque(){
        funcionario.removerItemEstoque(txtCodigoRemover.getText());
    }

    public void cancelar(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaFuncionario.fxml", 600,400);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
