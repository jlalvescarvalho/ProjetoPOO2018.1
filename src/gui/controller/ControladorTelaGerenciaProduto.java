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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import negocio.entidade.Produto;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorTelaGerenciaProduto implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    IFachadaFuncionario funcionario = new Fachada();

    private Stage tela;
    @FXML
    private Pane pane;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtCodigoUp;
    @FXML
    private TextField txtDescricaoUp;
    @FXML
    private TextField txtMarcaUp;
    @FXML
    private TextField txtPrecoUp;
    @FXML
    private TextField txtCodigoRemover;
    @FXML
    private ListView lstProdutos;
    @FXML
    private Label txtRemovido;



    public void cadastrarProduto(){

        try {
            verificarPreco(txtPreco.getText());
            funcionario.cadastrarProduto(txtCodigo.getText(), txtDescricao.getText(),Double.parseDouble(txtPreco.getText()),txtMarca.getText());


            limparCampoCadastrar();



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Produto cadastrado com sucesso !");
            alert.showAndWait();



        } catch (CodigoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }catch (ApenasNumerosException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (TamanhoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (DescricaoInvalidaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ProdutoJaExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ProdutoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (PrecoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void removerProduto(){

        try {
            Produto produto = funcionario.recuperarProduto(txtCodigoRemover.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Deseja mesmo remover "+produto.getDescricao()+" "+produto.getMarca()+"?");


            Optional<ButtonType> resultado = alert . showAndWait ();
            if (((Optional) resultado).get() == ButtonType.OK){
                funcionario.removerProduto(txtCodigoRemover.getText());
                txtCodigoRemover.setText("");
                txtRemovido.setText("Produto removiso com sucesso!");
                txtRemovido.setVisible(true);

            }

        } catch (CodigoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ProdutoNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void listarProdutos(){
        ArrayList<Produto> produtos = funcionario.recuperarTodosProdutos();
        if (produtos.isEmpty()){
            //ObservableList<String> observableListEmpty = FXCollections.observableArrayList("Não existem produtos cadastrados");
          // lstProdutos.setItems(observableListEmpty);
        }else{
            ObservableList<Produto> observableList = FXCollections.observableArrayList(produtos);
            lstProdutos.setItems(observableList);
        }


    }

    public void atualizarProduto(){
        Produto produto = null;
        try {
            verificarPreco(txtPrecoUp.getText());

            produto = funcionario.recuperarProduto(txtCodigoUp.getText());

            produto.setDescricao(txtDescricaoUp.getText());
            produto.setMarca(txtMarcaUp.getText());
            produto.setPreco(Double.parseDouble(txtPrecoUp.getText()));

            funcionario.atualizarProduto(txtCodigoUp.getText(), produto);

            limparCamposAtualizar();

        } catch (CodigoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ProdutoNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ApenasNumerosException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (TamanhoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (DescricaoInvalidaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (PrecoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }


    }

    public void preencherCamposAtualizar(){

        Produto p = null;
        try {
            p = funcionario.recuperarProduto(txtCodigoUp.getText());

            txtCodigoUp.setEditable(false);
            txtDescricaoUp.setText(p.getDescricao());
            txtMarcaUp.setText(p.getMarca());
            txtPrecoUp.setText(String.valueOf(p.getPreco()));


        } catch (CodigoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (ProdutoNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }



    }

    private void limparCamposAtualizar(){
        txtCodigoUp.setText("");
        txtCodigoUp.setEditable(true);
        txtDescricaoUp.setText("");
        txtMarcaUp.setText("");
        txtPrecoUp.setText("");

    }
    private void limparCampoCadastrar(){

        txtDescricao.setText("");
        txtCodigo.setText("");
        txtMarca.setText("");
        txtPreco.setText("");

    }

    private void verificarPreco(String txt) throws ApenasNumerosException {
        char[] precoChar = txt.toCharArray();
        for(int i = 0; i < precoChar.length; i++){
            if (!Character.isDigit(precoChar[i]) && precoChar[i] != '.'){
                throw new ApenasNumerosException("preco");
            }
        }
    }
    public void cancelar(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaFuncionario.fxml", 600,400);
    }



}
