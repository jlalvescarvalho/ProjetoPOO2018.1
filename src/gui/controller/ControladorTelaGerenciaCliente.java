package gui.controller;

import execoes.CPFApenasNumerosException;
import execoes.CPFTamanhoException;
import execoes.NomeInvalidoException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import negocio.entidade.Cliente;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaCliente implements Initializable {

    IFachadaFuncionario funcionario = new Fachada();


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
                    Integer.parseInt(txtNum.getText()),txtCidade.getText());

        } catch (CPFApenasNumerosException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (CPFTamanhoException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (NomeInvalidoException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        zerarCamposdeCadastro();

    }

    private void zerarCamposdeCadastro(){
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
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
