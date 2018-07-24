package gui.controller;

import fachada.FachadaFuncionario;
import fachada.FachadaGerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import negocio.entidade.Endereco;
import negocio.entidade.Usuario;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaUsuario implements Initializable {
    FachadaGerente fachadaGerente = new FachadaGerente();

    @FXML
    private TextField txtNomeCad;
    @FXML
    private TextField txtCpfCad;
    @FXML
    private TextField txtSenhaCad;
    @FXML
    private ComboBox<String> comBoxUsuario = new ComboBox<>();
    @FXML
    private TextField txtRuaCad;
    @FXML
    private TextField txtBairroCad;
    @FXML
    private TextField txtNumeroCad;
    @FXML
    private TextField txtCidadeCad;
    @FXML
    private TextField txtCepCad;
    @FXML
    private ListView<Usuario> lstVUsuarios;
    @FXML
    private Button btCadastrarCad;

    public void preencherListaUsuarios(){
        ArrayList<Usuario> listaUsuario = fachadaGerente.recuperarTodosUsuarios();
        ObservableList<Usuario> usuarioObservableList = FXCollections.observableArrayList(listaUsuario);
        lstVUsuarios.setItems(usuarioObservableList);
    }

    private void preencherComboBox(){
       ArrayList<String> list = new ArrayList<>();
       list.add("Gerente");
       list.add("Funcionario");

       ObservableList observableList = FXCollections.observableList(list);
        comBoxUsuario.setItems(observableList);
    }

    public void cadastrarUsuario(ActionEvent actionEvent){
        if (comBoxUsuario.getValue().equals("Gerente")){
            cadastrarGerente();
        }else if (comBoxUsuario.getValue().equals("Funcionario")){
            cadastrarFuncionario();
        }
    }

    private void cadastrarFuncionario(){
        fachadaGerente.cadastrarFuncionario(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                txtCepCad.getText(), Integer.parseInt(txtNumeroCad.getText()), txtCidadeCad.getText(), txtSenhaCad.getText());
    }
    private void cadastrarGerente(){
        fachadaGerente.cadastrarFuncionario(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                txtCepCad.getText(), Integer.parseInt(txtNumeroCad.getText()), txtCidadeCad.getText(), txtSenhaCad.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherComboBox();
    }
}
