package gui.controller;

import execoes.*;
import fachada.Fachada;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import negocio.entidade.Gerente;
import negocio.entidade.SalarioCargoEnum;
import negocio.entidade.Usuario;


import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaUsuario implements Initializable {
    IFachadaGerente gerente = new Fachada();

    private Stage tela;
    @FXML
    private Pane pane;
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
    private TextField txtNumFunc;
    @FXML
    private Label labelNumFunc;
    @FXML
    private ComboBox<String> comBoxUp;
    @FXML
    private TextField txtNumFuncUP;
    @FXML
    private Label labelNumFuncUP;
    @FXML
    private TextField txtCpfAtualizar;
    @FXML
    private TextField txtNomeAtualizar;
    @FXML
    private TextField txtSenhaAtualizar;
    @FXML
    private TextField txtRuaAtualizar;
    @FXML
    private TextField txtNumAtualizar;
    @FXML
    private TextField txtBairroAtualizar;
    @FXML
    private TextField txtCidadeAtualizar;
    @FXML
    private TextField txtCepAtualizar;
    @FXML
    private TextField txtSalarioUP;
    @FXML
    private TextField txtCpfRemover;



    public void preencherListaUsuarios(){
        ArrayList<Usuario> listaUsuario = gerente.recuperarTodosUsuarios();
        ObservableList<Usuario> usuarioObservableList = FXCollections.observableArrayList(listaUsuario);
        lstVUsuarios.setItems(usuarioObservableList);
    }

    public void actionComboBox(){
        SingleSelectionModel<String> selectionModel = comBoxUsuario.getSelectionModel();
        if(selectionModel.getSelectedItem().equals(SalarioCargoEnum.Gerente.name())){
            labelNumFunc.setVisible(true);
            txtNumFunc.setVisible(true);
        }else{
            labelNumFunc.setVisible(false);
            txtNumFunc.setVisible(false);
        }
    }

    public void actionComboBoxUpdate(){

        SingleSelectionModel<String> selectionModel = comBoxUp.getSelectionModel();
        if(selectionModel.getSelectedItem().equals(SalarioCargoEnum.Gerente.name())){
            labelNumFuncUP.setVisible(true);
            txtNumFuncUP.setVisible(true);
        }else{
            labelNumFuncUP.setVisible(false);
            txtNumFuncUP.setVisible(false);
        }
    }

    private void preencherComboBox(){
       ArrayList<String> list = new ArrayList<>();
       list.add(SalarioCargoEnum.Gerente.name());
       list.add(SalarioCargoEnum.Funcionario.name());

       ObservableList observableList = FXCollections.observableList(list);
        comBoxUsuario.setItems(observableList);
        comBoxUp.setItems(observableList);
    }

    private void preencherComboBoxUpdate(){

        ArrayList<String> list = new ArrayList<>();
        list.add(SalarioCargoEnum.Gerente.name());
        list.add(SalarioCargoEnum.Funcionario.name());

        ObservableList observableList = FXCollections.observableList(list);
        comBoxUp.setItems(observableList);
    }

    public void cadastrarUsuario() throws CampoTipoUsuarioVazioException {
        if (comBoxUsuario.getValue().equals("")){
            throw new CampoTipoUsuarioVazioException();
        }else {
            if (comBoxUsuario.getValue().equals(SalarioCargoEnum.Gerente.name())) {
                cadastrarGerente();
            } else if (comBoxUsuario.getValue().equals(SalarioCargoEnum.Funcionario.name())) {
                cadastrarFuncionario();
            }
        }
    }


    private void cadastrarFuncionario(){
        try {
            gerente.cadastrarFuncionario(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                    txtCepCad.getText(), txtNumeroCad.getText(), txtCidadeCad.getText(), txtSenhaCad.getText());


        } catch (UsuarioJaExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (UsuarioInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (CPFApenasNumerosException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (NomeInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (CPFTamanhoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CPFInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void cadastrarGerente(){
        try {
            gerente.cadastrarGerente(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                    txtCepCad.getText(), txtNumeroCad.getText(), txtCidadeCad.getText(),
                     txtSenhaCad.getText(), Integer.parseInt(txtNumFunc.getText()));

        } catch (UsuarioJaExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (UsuarioInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (CPFApenasNumerosException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (CPFTamanhoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (NomeInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        } catch (CPFInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void atualuzarUsuario(){


            Usuario usuario = buscarUsuario(txtCpfAtualizar.getText());
            if (usuario instanceof Gerente){
                ((Gerente) usuario).setNumeroDeFuncionariosGerenciados(Integer.parseInt(txtNumFuncUP.getText()));
            }
            usuario.setNome(txtNomeAtualizar.getText());
            usuario.setSenha(txtSenhaAtualizar.getText());
            usuario.setSalario(Double.parseDouble(txtSalarioUP.getText()));
            usuario.getEndereco().setRua(txtRuaAtualizar.getText());
            usuario.getEndereco().setBairro(txtBairroAtualizar.getText());
            usuario.getEndereco().setCep(txtCepAtualizar.getText());
            usuario.getEndereco().setNumero(txtNumAtualizar.getText());
            usuario.getEndereco().setCidade(txtCidadeAtualizar.getText());

            gerente.atualizarUsuario(txtCpfAtualizar.getText(), usuario);

            limparCamposAtualizar();


    }

    public void preencherCamposAtualizar(){

            Usuario usuario = buscarUsuario(txtCpfAtualizar.getText());
            if (usuario instanceof Gerente){
                comBoxUp.setValue(SalarioCargoEnum.Gerente.name());

                labelNumFunc.setVisible(true);
                txtNumFuncUP.setVisible(true);
                txtNumFuncUP.setText(String.valueOf(((Gerente) usuario).getNumeroDeFuncionariosGerenciados()));
            }

            txtCpfAtualizar.setEditable(false);
            txtNomeAtualizar.setText(usuario.getNome());
            txtRuaAtualizar.setText(usuario.getEndereco().getRua());
            txtNumAtualizar.setText(String.valueOf(usuario.getEndereco().getNumero()));
            txtBairroAtualizar.setText(usuario.getEndereco().getCidade());
            txtCepAtualizar.setText(usuario.getEndereco().getBairro());
            txtCidadeAtualizar.setText(usuario.getEndereco().getCep());
            txtSalarioUP.setText(String.valueOf(usuario.getSalario()));
            txtSenhaAtualizar.setText(usuario.getSenha());

    }

    private void limparCamposAtualizar(){

            comBoxUp.setValue("");

            labelNumFunc.setVisible(false);
            txtNumFuncUP.setVisible(false);
            txtNumFuncUP.setText("");
            txtCpfAtualizar.setEditable(true);
            txtNomeAtualizar.setText("");
            txtRuaAtualizar.setText("");
            txtNumAtualizar.setText("");
            txtBairroAtualizar.setText("");
            txtCepAtualizar.setText("");
            txtCidadeAtualizar.setText("");
            txtSalarioUP.setText("");
            txtSenhaAtualizar.setText("");
            txtCpfAtualizar.setText("");
    }

    public void removerUsuario(){

            Usuario usuario = buscarUsuario(txtCpfRemover.getText());

            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btSim = new ButtonType("Sim");
            ButtonType btNao = new ButtonType("Nao");

            alerte.setTitle("Remover");
            alerte.setHeaderText("Voce deseja realmente remover "+usuario.getNome()+" ?");
            alerte.showAndWait().ifPresent(b -> {
                if (b == btSim){

                    try {
                        gerente.removerUsuario(txtCpfRemover.getText());
                        txtCpfRemover.setText("");


                    } catch (UsuarioNaoExisteException e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Atencao");
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    } catch (UsuarioInvalidoException e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Atencao");
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    }

                }else if (b == btNao){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Usuario nao removido !");
                    alert.showAndWait();
                }
            });
    }
    private Usuario buscarUsuario(String cpf){
        Usuario usuario = null;
        try {
            usuario = gerente.recuperarUsuario(cpf);

        } catch (UsuarioNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return usuario;
    }

    public void cancelar(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerente.fxml", 600,400);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherComboBox();
        preencherComboBoxUpdate();
    }
}
