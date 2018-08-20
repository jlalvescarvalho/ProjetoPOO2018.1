package gui.controller;

import execoes.*;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.Cliente;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaCliente implements Initializable {

    IFachadaFuncionario funcionario = new Fachada();

    private Stage tela;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtNum;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtNomeAtualizar;
    @FXML
    private TextField txtCpfAtualizar;
    @FXML
    private Label labelCpf;
    @FXML
    private Label labelCpfNome;
    @FXML
    private TextField txtRuaAtualizar;
    @FXML
    private TextField txtCidadeAtualizar;
    @FXML
    private TextField txtNumAtualizar;
    @FXML
    private TextField txtCepAtualizar;
    @FXML
    private TextField txtBairroAtualizar;
    @FXML
    private TextField txtCpfRemover;
    @FXML
    private ListView<Cliente> lstCliente;

    public void cadastrarClinte(){
        try {
            funcionario.cadastrarCliente(txtNome.getText(), txtCpf.getText(), txtRua.getText(), txtBairro.getText(),txtCep.getText(),
                    txtNum.getText(),txtCidade.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cliente cadastrado com sucesso !");
            alert.showAndWait();

            limparCamposdeCadastro();

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
        } catch (ClienteJaExiteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CampoEnderecoVazioException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CepInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }


    }

    private void limparCamposdeCadastro(){
        txtNome.setText("");
        txtCpf.setText("");
        txtRua.setText("");
        txtCidade.setText("");
        txtBairro.setText("");
        txtNum.setText("");
        txtCep.setText("");
    }

    public void removerCliente(){
        Cliente c = null;
        try {
            c = funcionario.recuperarCliente(txtCpfRemover.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Deseja realmente remover "+c.getNome()+" ? ");

            Optional<ButtonType> resultado = alert . showAndWait ();
            if (((Optional) resultado).get() == ButtonType.OK){
                funcionario.removerCliente(c);

            }
            txtCpfRemover.setText("");

            Alert alerte = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Removido");
            alert.setHeaderText("Removido com sucesso !");
            alert.showAndWait();

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }



    }

    public void listarClientes(){
        ArrayList<Cliente> listaClientes = funcionario.recuperarTodosCliente();
        ObservableList observableList = FXCollections.observableArrayList(listaClientes);

        lstCliente.setItems(observableList);
    }

    public void atualizarCliente(){

        try {
            Cliente cliente = funcionario.recuperarCliente(txtCpfAtualizar.getText());


            cliente.setNome(txtNomeAtualizar.getText());
            cliente.getEndereco().setRua(txtRuaAtualizar.getText());
            cliente.getEndereco().setNumero(txtNumAtualizar.getText());
            cliente.getEndereco().setBairro(txtBairroAtualizar.getText());
            cliente.getEndereco().setCidade(txtCidadeAtualizar.getText());
            cliente.getEndereco().setCep(txtCepAtualizar.getText());

            funcionario.atualizarCliente(txtCpfAtualizar.getText(), cliente);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cliente atualizado com sucesso !");
            alert.showAndWait();

            limparCamposAtualizar();

        } catch (NomeInvalidoException e) {
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

        } catch (CPFInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cpf nao encontrado!");
            alert.showAndWait();
        } catch (CampoEnderecoVazioException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cpf nao encontrado!");
            alert.showAndWait();
        } catch (CepInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cpf nao encontrado!");
            alert.showAndWait();
        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cep Apenas numero");
            alert.showAndWait();
        }


    }

    public void preencherCamposAtualizar(){
        Cliente cliente = null;
        try {
            cliente = funcionario.recuperarCliente(txtCpfAtualizar.getText());

            txtCpfAtualizar.setEditable(false);
            labelCpfNome.setVisible(true);
            labelCpf.setText(cliente.getCpf());
            txtNomeAtualizar.setText(cliente.getNome());
            txtCidadeAtualizar.setText(cliente.getEndereco().getCidade());
            txtRuaAtualizar.setText(cliente.getEndereco().getRua());
            txtNumAtualizar.setText(String.valueOf(cliente.getEndereco().getNumero()));
            txtBairroAtualizar.setText(cliente.getEndereco().getBairro());
            txtCepAtualizar.setText(cliente.getEndereco().getCep());
            txtCpfAtualizar.setText("");

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cliente não encontrado");
            alert.showAndWait();
        }


    }

    private void limparCamposAtualizar(){
        txtCpfAtualizar.setEditable(true);
        txtCpfAtualizar.setText("");
        txtNomeAtualizar.setText("");
        txtCidadeAtualizar.setText("");
        txtRuaAtualizar.setText("");
        txtNumAtualizar.setText("");
        txtBairroAtualizar.setText("");
        txtCepAtualizar.setText("");
    }

    public void cancelar(ActionEvent actionEvent) throws IOException {
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();

        Main.chamarTela("view/TelaFuncionario.fxml", 600,400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
