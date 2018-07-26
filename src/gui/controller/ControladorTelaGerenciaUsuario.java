package gui.controller;

import execoes.UsuarioInvalidoException;
import execoes.UsuarioJaExisteException;
import fachada.Fachada;
import fachada.IFachadaGerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import negocio.entidade.Usuario;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaUsuario implements Initializable {
    IFachadaGerente gerente = new Fachada();

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
    private TextField txtSalario;
    @FXML
    private TextField txtNumFunc;
    @FXML
    private Label labelNumFunc;
    @FXML
    private ComboBox<String> comBoxUp;
    @FXML
    private TextField txtNumFuncUP;
    @FXML
    private Label labelNumFuncUP;



    public void preencherListaUsuarios(){
        ArrayList<Usuario> listaUsuario = gerente.recuperarTodosUsuarios();
        ObservableList<Usuario> usuarioObservableList = FXCollections.observableArrayList(listaUsuario);
        lstVUsuarios.setItems(usuarioObservableList);
    }

    public void actionComboBox(){
        SingleSelectionModel<String> selectionModel = comBoxUsuario.getSelectionModel();
        if(selectionModel.getSelectedItem().equals("Gerente")){
            labelNumFunc.setVisible(true);
            txtNumFunc.setVisible(true);
        }else{
            labelNumFunc.setVisible(false);
            txtNumFunc.setVisible(false);
        }
    }

    public void actionComboBoxUpdate(){

        SingleSelectionModel<String> selectionModel = comBoxUp.getSelectionModel();
        if(selectionModel.getSelectedItem().equals("Gerente")){
            labelNumFuncUP.setVisible(true);
            txtNumFuncUP.setVisible(true);
        }else{
            labelNumFuncUP.setVisible(false);
            txtNumFuncUP.setVisible(false);
        }
    }

    private void preencherComboBox(){
       ArrayList<String> list = new ArrayList<>();
       list.add("Gerente");
       list.add("Funcionario");

       ObservableList observableList = FXCollections.observableList(list);
        comBoxUsuario.setItems(observableList);
        comBoxUp.setItems(observableList);
    }

    private void preencherComboBoxUpdate(){

        ArrayList<String> list = new ArrayList<>();
        list.add("Gerente");
        list.add("Funcionario");

        ObservableList observableList = FXCollections.observableList(list);
        comBoxUp.setItems(observableList);
    }

    public void cadastrarUsuario(ActionEvent actionEvent){
        if (comBoxUsuario.getValue().equals("Gerente")){
            cadastrarGerente();
        }else if (comBoxUsuario.getValue().equals("Funcionario")){
            cadastrarFuncionario();
        }
    }


    private void cadastrarFuncionario(){
        try {
            gerente.cadastrarFuncionario(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                    txtCepCad.getText(), Integer.parseInt(txtNumeroCad.getText()), txtCidadeCad.getText(),
                    Double.parseDouble(txtSalario.getText()), txtSenhaCad.getText());
        } catch (UsuarioJaExisteException e) {
            e.printStackTrace();
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
        }
    }
    private void cadastrarGerente(){
        try {
            gerente.cadastrarGerente(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                    txtCepCad.getText(), Integer.parseInt(txtNumeroCad.getText()), txtCidadeCad.getText(),
                    Double.parseDouble(txtSalario.getText()), txtSenhaCad.getText(), Integer.parseInt(txtNumFunc.getText()));
        } catch (UsuarioJaExisteException e) {
            e.printStackTrace();
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherComboBox();
        preencherComboBoxUpdate();
    }
}
