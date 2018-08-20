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
import java.util.Optional;
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
    private TextField txtNumFuncUP;
    @FXML
    private Label labelNumFuncUP;
    @FXML
    private TextField txtCpfAtualizar;
    @FXML
    private TextField txtNomeAtualizar;
    @FXML
    private PasswordField txtSenhaAtualizar;
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


    private void preencherComboBox(){
       ArrayList<String> list = new ArrayList<>();
       list.add(SalarioCargoEnum.Gerente.name());
       list.add(SalarioCargoEnum.Funcionario.name());

       ObservableList observableList = FXCollections.observableList(list);
       comBoxUsuario.setItems(observableList);
    }


    public void cadastrarUsuario(){
       try{
           if (comBoxUsuario.getValue().equals(SalarioCargoEnum.Gerente.name())) {
                cadastrarGerente();
            } else if (comBoxUsuario.getValue().equals(SalarioCargoEnum.Funcionario.name())) {
                cadastrarFuncionario();
            }
        }catch(NullPointerException ne){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Atencao");
           alert.setHeaderText("Nao é possivel cadastar !");
           alert.showAndWait();
       }

    }


    private void cadastrarFuncionario(){
        try {
            gerente.cadastrarFuncionario(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                    txtCepCad.getText(), txtNumeroCad.getText(), txtCidadeCad.getText(), txtSenhaCad.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro Usuario");
            alert.setHeaderText("Funcionario cadastrado com sucesso !");
            alert.showAndWait();

            limparCamposCadastrar();

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
        } catch (SenhaInvalidaException e) {
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
        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cep Apenas numero");
            alert.showAndWait();
        }
    }

    private void cadastrarGerente(){
        try {
            gerente.cadastrarGerente(txtNomeCad.getText(),txtCpfCad.getText(), txtRuaCad.getText(), txtBairroCad.getText(),
                    txtCepCad.getText(), txtNumeroCad.getText(), txtCidadeCad.getText(),
                     txtSenhaCad.getText(), Integer.parseInt(txtNumFunc.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro Usuario");
            alert.setHeaderText("Gerente cadastrado com sucesso !");
            alert.showAndWait();

            limparCamposCadastrar();

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
        } catch (SenhaInvalidaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }catch (NumberFormatException nbe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Campo numero de funcionario deve ser preenchido");
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

    public void atualizarUsuario(){

        try {
            Usuario usuario = gerente.recuperarUsuario(txtCpfAtualizar.getText());
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
        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cep Apenas numero");
            alert.showAndWait();
        }catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Campos Vazios");
            alert.showAndWait();
        } catch (UsuarioNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
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
        }


    }

    public void preencherCamposAtualizar(){
        try {
            Usuario usuario = gerente.recuperarUsuario(txtCpfAtualizar.getText());

            if (usuario instanceof Gerente) {
                labelNumFuncUP.setVisible(true);
                txtNumFuncUP.setVisible(true);
                txtNumFuncUP.setText(String.valueOf(((Gerente) usuario).getNumeroDeFuncionariosGerenciados()));
            }


            txtCpfAtualizar.setEditable(false);
            txtNomeAtualizar.setText(usuario.getNome());
            txtRuaAtualizar.setText(usuario.getEndereco().getRua());
            txtNumAtualizar.setText(String.valueOf(usuario.getEndereco().getNumero()));
            txtBairroAtualizar.setText(usuario.getEndereco().getBairro());
            txtCepAtualizar.setText(usuario.getEndereco().getCep());
            txtCidadeAtualizar.setText(usuario.getEndereco().getCidade());
            txtSalarioUP.setText(String.valueOf(usuario.getSalario()));
            txtSenhaAtualizar.setText(usuario.getSenha());


        } catch (UsuarioNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
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
        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cpf apenas numeros");
            alert.showAndWait();
        }catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencao");
            alert.setHeaderText("Usuario não encontrado !");
            alert.showAndWait();
        }


    }

    private void limparCamposAtualizar(){

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


            try {
                Usuario usuario = gerente.recuperarUsuario(txtCpfRemover.getText());

                Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
                alerte.setTitle("Remover");
                alerte.setHeaderText("Voce deseja realmente remover " + usuario.getNome() + " ?");
                Optional<ButtonType> resultado = alerte.showAndWait();

                if (((Optional) resultado).get() == ButtonType.OK) {

                    gerente.removerUsuario(txtCpfRemover.getText());
                    txtCpfRemover.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Usuario removido com sucesso !");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Usuario nao removido !");
                    alert.showAndWait();
                }

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
            }


    }

    private void limparCamposCadastrar(){
        txtNomeCad.setText("");
        txtCpfCad.setText("");
        txtSenhaCad.setText("");
        comBoxUsuario.setValue("");
        txtRuaCad.setText("");
        txtNumeroCad.setText("");
        txtBairroCad.setText("");
        txtCidadeCad.setText("");
        txtBairroCad.setText("");
        txtCepCad.setText("");
    }

    public void cancelar(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerente.fxml", 600,400);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherComboBox();
    }
}
