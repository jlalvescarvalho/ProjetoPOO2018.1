package gui.controller;

import execoes.CodigoInvalidoException;
import execoes.ProdutoNaoExisteException;
import execoes.QuantidadeNaoDisponivelException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
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
import negocio.entidade.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaVendas implements Initializable {

    IFachadaFuncionario funcionario = new Fachada();
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
    private static double subTotalASerPassado;


    public void adicionarProduto(){

            try {
                funcionario.adicionarItem(codigoProdutoVenda.getText(), Integer.parseInt(quantProdutoVenda.getText()));

                subTotalVenda.setText(String.valueOf(calcularTotalVenda()));
                atualizarTableView();


            }catch (QuantidadeNaoDisponivelException e){
                e.printStackTrace();
            }catch (CodigoInvalidoException e)  {
                e.printStackTrace();
            }catch (ProdutoNaoExisteException e)  {
                e.printStackTrace();
            }


    }

    private double calcularTotalVenda(){
        ArrayList<ItemVenda> itensVenda = funcionario.getListarItens();
        double total = 0.0;

        for (ItemVenda iv: itensVenda){
            total += iv.getTotalItem();
        }
        subTotalASerPassado = total;
        return total;
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

        for (ItemVenda iv: itemVendas){
            VendaModelo vendaModelo = new VendaModelo(iv.getId(), iv.getProduto().getDescricao(), iv.getQuantidade(),
                    iv.getProduto().getPreco(), iv.getTotalItem());
            itensConvertidos.add(vendaModelo);
        }

        return itensConvertidos;
    }

    public void cancelar(ActionEvent actionEvent) throws IOException {
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();

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
        if(cpfClienteVenda == null){
            labelCliente.setText("Avulso");
        }else {
            Cliente cliente = funcionario.recuperarCliente(cpfClienteVenda);

            labelCliente.setText(cliente.getNome());
        }
    }

    public void finalizarVenda(){
        if (cpfClienteVenda != null) {

            Main.chamarTela("view/TelaFinalizaVendaComCliente.fxml", 600, 400);
            tela = (Stage) this.pane.getScene().getWindow();
            tela.close();

        }else{
            funcionario.cadastrarVendaSemCliente((Funcionario) Login.getInstance().getUsuario());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Venda cadastrada com sucesso !");
            alert.showAndWait();

            Main.chamarTela("view/TelaFuncionario.fxml", 600, 400);
            tela = (Stage) this.pane.getScene().getWindow();
            tela.close();
        }

    }

    protected static String getCpfClienteVenda(){
       return cpfClienteVenda;
    }
    protected static double getSubTotal(){
        return subTotalASerPassado;
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
