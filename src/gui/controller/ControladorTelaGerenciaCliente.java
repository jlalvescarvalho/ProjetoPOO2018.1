package gui.controller;

import execoes.CPFApenasNumerosException;
import execoes.CPFTamanhoException;
import execoes.NomeInvalidoException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.Cliente;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

        } catch (CPFApenasNumerosException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (CPFTamanhoException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (NomeInvalidoException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        limparCamposdeCadastro();

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
        Cliente c = funcionario.recuperarCliente(txtCpfRemover.getText());
        int resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente remover "+c.getNome()+" ? ");
        if (resposta == 0) funcionario.removerCliente(c);

        txtCpfRemover.setText("");
    }

    public void listarClientes(){
        ArrayList<Cliente> listaClientes = funcionario.recuperarTodosCliente();
        ObservableList observableList = FXCollections.observableArrayList(listaClientes);

        lstCliente.setItems(observableList);
    }

    public void atualizarCliente(){

        Cliente cliente = funcionario.recuperarCliente(txtCpfAtualizar.getText());
        try {
            cliente.setNome(txtNomeAtualizar.getText());
            cliente.getEndereco().setRua(txtRuaAtualizar.getText());
            cliente.getEndereco().setNumero(txtNumAtualizar.getText());
            cliente.getEndereco().setBairro(txtBairroAtualizar.getText());
            cliente.getEndereco().setCidade(txtCidadeAtualizar.getText());
            cliente.getEndereco().setCep(txtCepAtualizar.getText());

            funcionario.atualizarCliente(txtCpfAtualizar.getText(), cliente);

            limparCamposAtualizar();
        } catch (NomeInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }



    }

    public void preencherCamposAtualizar(){
        Cliente cliente = funcionario.recuperarCliente(txtCpfAtualizar.getText());

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
