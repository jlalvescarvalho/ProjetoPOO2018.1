package gui.controller;

import execoes.*;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.*;

import java.net.URL;
import java.time.format.TextStyle;
import java.util.ResourceBundle;

import static gui.controller.ControladorTelaVendas.*;

public class ControladorTelaFinalizaVenda implements Initializable {

    IFachadaGerente gerente = new Fachada();
    IFachadaFuncionario funcionario = new Fachada();

    private Stage tela;
    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelSubTotal;
    @FXML
    private Label labelDesconto;
    @FXML
    private Label labelTotalApagar;

    private double descontoGerente;



    /**
     * Metodo para preencher os campos;
     *
     * @param desconto
     */
    private void preencherCampos(double desconto) {
        double subTotal = funcionario.calcularTotalVenda();
        labelSubTotal.setText(String.valueOf(subTotal));
        labelDesconto.setText(String.valueOf(desconto * 100) + " %");
        labelTotalApagar.setText(String.valueOf(subTotal - (subTotal * desconto)));
    }

    /**
     * faz-se necessario salvar a venda passando o desconto para que o total seja valido quando for calculado o total;
     */
    public void finalizarVendaComCliente() {
        Cliente c = null;
        try {
            c = funcionario.recuperarCliente(getCpfClienteVenda());

            funcionario.cadastrarVendaComCliente((Funcionario) Login.getInstance().getUsuario(), c, descontoGerente);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Venda cadastrada com sucesso !");
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
        } catch (VendaVaziaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }


        Main.chamarTela("view/TelaFuncionario.fxml", 600, 400);
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    private static void verificarCampoCpf(String cpf) throws CPFTamanhoException, CPFApenasNumerosException {
        char[] cpfChar = cpf.toCharArray();
        for (int i = 0; i < cpfChar.length; i++) {
            if (!Character.isDigit(cpfChar[i])) {
                throw new CPFApenasNumerosException();
            }
        }
        if (cpf.length() != 11) {
            throw new CPFTamanhoException(cpf.length());
        }
    }

    protected static String autenticarGerente() throws UsuarioInvalidoException {
        final String[] cpf = new String[1];
        final String[] senha = new String[1];

        try {
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setTitle("Autenticacao do gerente");
            inputDialog.setContentText("Login/CPF: ");
            inputDialog.showAndWait().ifPresent(v -> cpf[0] = v);

            verificarCampoCpf(cpf[0]);


            TextInputDialog inputDialog1 = new TextInputDialog();
            inputDialog1.setTitle("Autenticacao do gerente");
            inputDialog1.setContentText("senha: ");
            inputDialog1.showAndWait().ifPresent(v -> senha[0] = v);

            return cpf[0];

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
        }catch (NullPointerException ne){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Cpf nao identificado");
            alert.showAndWait();
        }
        throw new UsuarioInvalidoException();
    }


    /***
     * Caso a compra seja maior que 300 reais o gerente 'PODE' dar uma desconto adicional ao cliente;
     * Sendo necessario a autenticação do mesmo;
     */
    public void DescontoGerente() {

        final String[] desconto = new String[1];
        try {
            Usuario gerent = (Gerente)gerente.recuperarUsuario(autenticarGerente());

            TextInputDialog inputDialog2 = new TextInputDialog();
            inputDialog2.setTitle("Desconto Gerente");
            inputDialog2.setHeaderText(gerent.getNome());
            inputDialog2.setContentText("% de desconto: ");
            inputDialog2.showAndWait().ifPresent(v -> desconto[0] = v);

            descontoGerente = gerente.darDesconto(gerent.getCpf(), gerent.getSenha(), funcionario.calcularTotalVenda(), Double.parseDouble(desconto[0]));

            preencherCampos(descontoGerente);


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
        } catch (DescontoInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }catch (NullPointerException ne){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Nao é possivel localizar o cliente");
            alert.showAndWait();
        }catch (ClassCastException cce){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText("Dados inseridos nao sao de Gerente");
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

    /***
     * Inicialização do(s) label(s) com o valor da venda e se for a 10 compra do cliente ele já recebe um desconto
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double desconto = 0;
        try {
            desconto = funcionario.verificarFrequencia(getCpfClienteVenda());

            preencherCampos(desconto);


        } catch (CPFInvalidoException e) {
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
        }

    }
}
