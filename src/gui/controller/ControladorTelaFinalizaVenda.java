package gui.controller;

import execoes.*;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.entidade.Login;
import negocio.entidade.Usuario;

import java.net.URL;
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
    final String[] cpf = new String[1];
    final String[] senha = new String[1];
    final String[] desconto = new String[1];


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
        Cliente c = funcionario.recuperarCliente(getCpfClienteVenda());
        funcionario.cadastrarVendaComCliente((Funcionario) Login.getInstance().getUsuario(), c, descontoGerente);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Venda cadastrada com sucesso !");
        alert.showAndWait();

        Main.chamarTela("view/TelaFuncionario.fxml", 600, 400);
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    private void verificarCampoCpf(String cpf) throws CPFTamanhoException, CPFApenasNumerosException {
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

    private void autenticarGerente() {
        try {
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setTitle("Autenticação do gerente");
            inputDialog.setContentText("Login/CPF: ");
            inputDialog.showAndWait().ifPresent(v -> cpf[0] = v);

            verificarCampoCpf(cpf[0]);

            TextInputDialog inputDialog1 = new TextInputDialog();
            inputDialog1.setTitle("Autenticação do gerente");
            inputDialog1.setContentText("senha: ");
            inputDialog1.showAndWait().ifPresent(v -> senha[0] = v);


        } catch (CPFTamanhoException e) {
            e.printStackTrace();
        } catch (CPFApenasNumerosException e) {
            e.printStackTrace();
        }

    }


    /***
     * Caso a compra seja maior que 300 reais o gerente 'PODE' dar uma desconto adicional ao cliente;
     * Sendo necessario a autenticação do mesmo;
     */
    public void DescontoGerente() {


        Usuario gerent = null;
        try {
            gerent = gerente.recuperarUsuario(cpf[0]);

            TextInputDialog inputDialog2 = new TextInputDialog();
            inputDialog2.setTitle("Desconto Gerente");
            inputDialog2.setHeaderText(gerent.getNome());
            inputDialog2.setContentText("% de desconto: ");
            inputDialog2.showAndWait().ifPresent(v -> desconto[0] = v);

            Usuario usuarioTemp = Login.getInstance().getUsuario();

            if (Login.getInstance().verificarLogin(cpf[0], senha[0]) == 1) {

                descontoGerente = gerente.darDesconto(cpf[0], senha[0], funcionario.calcularTotalVenda(), Double.parseDouble(desconto[0]));

                preencherCampos(descontoGerente);

                Login.getInstance().setUsuario(usuarioTemp);
            }

        } catch (UsuarioNaoExisteException e) {
            e.printStackTrace();
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
        } catch (DescontoInvalidoException e) {
            e.printStackTrace();
        }


    }

    /***
     * Inicialização do(s) label(s) com o valor da venda e se for a 10 compra do cliente ele já recebe um desconto
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double desconto = funcionario.verificarFrequencia(getCpfClienteVenda());
        preencherCampos(desconto);
    }
}
