package gui.controller;

import execoes.*;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.NegocioVenda;
import negocio.entidade.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaVendas implements Initializable {

    IFachadaFuncionario funcionario = new Fachada();
    IFachadaGerente gerente = new Fachada();
    private Stage tela;


    @FXML
    private AnchorPane pane;
    @FXML
    private TableView tableVenda;
    @FXML
    private TextField codigoProdutoVenda;
    @FXML
    private TextField quantProdutoVenda;
    @FXML
    private Label subTotalVenda;
    @FXML
    private TableColumn<VendaModelo, Integer> tcItem;
    @FXML
    private TableColumn<VendaModelo, String> tcDescricao;
    @FXML
    private TableColumn<VendaModelo, Integer> tcQuantidade;
    @FXML
    private TableColumn<VendaModelo, Double> tcPrecoUnit;
    @FXML
    private TableColumn<VendaModelo, Double> tcTotalItem;
    @FXML
    private Label labelCliente;
    @FXML
    private Label labelFuncionario;

    private ObservableList vendaModelos;
    private static String cpfClienteVenda;


    public void adicionarProduto(){

            try {
                verificarCamposVenda("Codigo",codigoProdutoVenda.getText());
                verificarCamposVenda("Quantidade",quantProdutoVenda.getText());
                funcionario.adicionarItem(codigoProdutoVenda.getText(), Integer.parseInt(quantProdutoVenda.getText()));

                subTotalVenda.setText(String.valueOf(funcionario.calcularTotalVenda()));
                atualizarTableView();
                codigoProdutoVenda.setText("");
                quantProdutoVenda.setText("");

            }catch (QuantidadeNaoDisponivelException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }catch (CodigoInvalidoException e)  {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }catch (ProdutoNaoExisteException e)  {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            } catch (ApenasNumerosException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }


    }

    private void atualizarTableView(){
        tcItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tcPrecoUnit.setCellValueFactory(new PropertyValueFactory<>("precoUnit"));
        tcTotalItem.setCellValueFactory(new PropertyValueFactory<>("total"));



        vendaModelos = FXCollections.observableArrayList(converterParaVendaModelo());
        tableVenda.setItems(vendaModelos);
    }



    /***
     * Metodo para converter os itens temporarios da venda que estão no NegocioVenda para VendaModelo. Afim de lançar no TableView
     * @return um ArrayList de VendaModelo
     */
    private ArrayList<VendaModelo> converterParaVendaModelo(){
        ArrayList<ItemVenda> itemVendas = funcionario.getListarItens();
        ArrayList<VendaModelo> itensConvertidos = new ArrayList<>();
        int contador = 0;


        for (ItemVenda iv: itemVendas){
            VendaModelo vendaModelo = new VendaModelo(contador++, iv.getProduto().getDescricao(), iv.getQuantidade(),
                    iv.getProduto().getPreco(), iv.getTotalItem());
            itensConvertidos.add(vendaModelo);
        }

        return itensConvertidos;
    }

    public void cancelar(){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();

        funcionario.esvaziarListaVenda();
        Main.chamarTela("view/TelaFuncionario.fxml", 600,400);
    }

    private void solicitarCpfVenda(){

        final String[] cpf = new String[1];
        TextInputDialog inputDialog = new TextInputDialog();

        inputDialog.setTitle("Cpf na venda");
        inputDialog.setHeaderText("Deseja colocar cpf na nota?");
        inputDialog.setContentText("Cpf: ");

        inputDialog.showAndWait().ifPresent(v -> cpf[0] = v);

        cpfClienteVenda = cpf[0];
        preencherLabelNomeCliente();
    }

    private void preencherLabelNomeCliente(){
        if(cpfClienteVenda == null || cpfClienteVenda.equals("") || cpfClienteVenda.equals(" ")){
            labelCliente.setText("Avulso");
        }else {
            Cliente cliente = null;
            try {
                cliente = funcionario.recuperarCliente(cpfClienteVenda);
                labelCliente.setText(cliente.getNome());

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
    }

    public void finalizarVenda() {
        if (cpfClienteVenda == null || cpfClienteVenda.equals(" ") || cpfClienteVenda.equals("")) {
            try {
                funcionario.cadastrarVendaSemCliente((Funcionario) Login.getInstance().getUsuario());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacao");
                alert.setHeaderText("Venda cadastrada com sucesso !");
                alert.showAndWait();

                Main.chamarTela("view/TelaFuncionario.fxml", 600, 400);
                tela = (Stage) this.pane.getScene().getWindow();
                tela.close();

            } catch (VendaVaziaException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }

        }else{
            Main.chamarTela("view/TelaFinalizaVendaComCliente.fxml", 600, 400);
            tela = (Stage) this.pane.getScene().getWindow();
            tela.close();
        }

    }

    protected static String getCpfClienteVenda(){
       return cpfClienteVenda;
    }

    private void verificarCamposVenda(String campo , String str) throws ApenasNumerosException, CodigoInvalidoException {
        if (str.equals("") || str.equals(" ")){
            throw new CodigoInvalidoException();
        }
        char[] campoChar = str.toCharArray();
        for(int i = 0; i < campoChar.length; i++){
            if (!Character.isDigit(campoChar[i])){
                throw new ApenasNumerosException(campo);
            }
        }
    }

    public void removerItemVenda(){
        int indice = identificarItemARemover();

            try {
                gerente.removerItem(indice);
                subTotalVenda.setText(String.valueOf(funcionario.calcularTotalVenda()));
                atualizarTableView();

            }catch (NullPointerException npe){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText("Numero de item nao existe");
                alert.showAndWait();
            } catch (CodigoInvalidoException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }catch (IndexOutOfBoundsException iobe){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atencao");
                alert.setHeaderText("Indice do item invalido !");
                alert.showAndWait();
            }

    }

    private int identificarItemARemover(){
        final String[] numeroItem = new String[1];
        try {
            String cpf = ControladorTelaFinalizaVenda.autenticarGerente();

            Usuario usuario = (Gerente)gerente.recuperarUsuario(cpf);

            TextInputDialog inputDialog2 = new TextInputDialog();
            inputDialog2.setTitle("Remover Item");
            inputDialog2.setHeaderText(usuario.getNome());
            inputDialog2.setContentText("Numero do item a ser removido: ");
            inputDialog2.showAndWait().ifPresent(v -> numeroItem[0] = v);

            return Integer.parseInt(numeroItem[0]);

        } catch (UsuarioInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (UsuarioNaoExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }catch (ClassCastException cce){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Os dados inseridos nao pertencem a um Gerente");
            alert.showAndWait();
        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Digite apenas numeros");
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
        return -1;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarTableView();
        solicitarCpfVenda();
        labelFuncionario.setText(Login.getInstance().getUsuario().getNome());
    }

    public static class VendaModelo {

        private final SimpleIntegerProperty item;
        private final SimpleStringProperty descricao;
        private final SimpleIntegerProperty quantidade;
        private final SimpleDoubleProperty precoUnit;
        private final SimpleDoubleProperty total;

        public VendaModelo(int item, String descricao, int quantidade, double precoUnit, double total) {
            this.item = new SimpleIntegerProperty(item);
            this.descricao = new SimpleStringProperty(descricao);
            this.quantidade = new SimpleIntegerProperty(quantidade);
            this.precoUnit = new SimpleDoubleProperty(precoUnit);
            this.total = new SimpleDoubleProperty(total);
        }


        public int getItem() {
            return item.get();
        }

        public SimpleIntegerProperty itemProperty() {
            return item;
        }

        public void setItem(int item) {
            this.item.set(item);
        }

        public String getDescricao() {
            return descricao.get();
        }

        public SimpleStringProperty descricaoProperty() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao.set(descricao);
        }

        public int getQuantidade() {
            return quantidade.get();
        }

        public SimpleIntegerProperty quantidadeProperty() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade.set(quantidade);
        }

        public double getPrecoUnit() {
            return precoUnit.get();
        }

        public SimpleDoubleProperty precoUnitProperty() {
            return precoUnit;
        }

        public void setPrecoUnit(double precoUnit) {
            this.precoUnit.set(precoUnit);
        }

        public double getTotal() {
            return total.get();
        }

        public SimpleDoubleProperty totalProperty() {
            return total;
        }

        public void setTotal(double total) {
            this.total.set(total);
        }
    }
}
