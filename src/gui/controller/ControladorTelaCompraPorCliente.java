package gui.controller;

import execoes.CPFApenasNumerosException;
import execoes.CPFInvalidoException;
import execoes.CPFTamanhoException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.Cliente;
import negocio.entidade.Venda;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaCompraPorCliente implements Initializable {

    IFachadaGerente gerente = new Fachada();
    IFachadaFuncionario funcionario = new Fachada();

    private Stage tela;
    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCliente;
    @FXML
    private ListView lstCompras;
    private static String cpfCliente;


    private void solicitarCpfCliente(){
            final String[] cpf = new String[1];

            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setTitle("Solicitacao Cpf");
            inputDialog.setHeaderText("Digite o cpf do cliente ");
            inputDialog.setContentText("Cpf: ");

            inputDialog.showAndWait().ifPresent(v -> cpf[0] = v);
            cpfCliente = cpf[0];
            preencherListView();
    }

    private void preencherListView(){

            if (cpfCliente == null || cpfCliente.equals("") || cpfCliente.equals(" ")){

            }else{
                try {
                    Cliente c = funcionario.recuperarCliente(cpfCliente);

                    labelCliente.setText(c.getNome());

                    ArrayList<Venda> vendas = filtrarVendas(cpfCliente);
                    ObservableList<Venda> ob = FXCollections.observableArrayList(vendas);
                    lstCompras.setItems(ob);


                } catch (CPFInvalidoException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencao");
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                } catch (CPFTamanhoException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencao");
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                } catch (CPFApenasNumerosException e) {
                    e.printStackTrace();Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencao");
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                }

            }



    }

    private ArrayList<Venda> filtrarVendas(String cpf){
        ArrayList<Venda> todasAsVendas = gerente.recuperarTodasVendas();
        ArrayList<Venda> vendasFiltradas = new ArrayList<>();

        for (Venda v: todasAsVendas){
            if (v.getCliente() != null && v.getCliente().getCpf().equals(cpf)){
                vendasFiltradas.add(v);
            }
        }
        return vendasFiltradas;

    }

    public void cancelar(){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerente.fxml", 600,400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        solicitarCpfCliente();
    }
}
