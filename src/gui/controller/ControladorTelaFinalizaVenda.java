package gui.controller;

import execoes.DescontoInvalidoException;
import execoes.UsuarioInvalidoException;
import fachada.Fachada;
import fachada.IFachadaFuncionario;
import fachada.IFachadaGerente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.entidade.Login;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.controller.ControladorTelaVendas.*;

public class ControladorTelaFinalizaVenda implements Initializable {

    IFachadaGerente gerente = new Fachada();
    IFachadaFuncionario funcionario = new Fachada();

    @FXML
    private Label labelSubTotal;
    @FXML
    private Label labelDesconto;
    @FXML
    private Label labelTotalApagar;



   public void preencherCampos(){
       double subtotal = getSubTotal();
       double desconto = funcionario.verificarFrequencia(getCpfClienteVenda());

       labelSubTotal.setText(String.valueOf(subtotal));
       labelDesconto.setText(String.valueOf(desconto));
       labelTotalApagar.setText(String.valueOf(subtotal-(subtotal*desconto)));
   }

   public void finalizarVendaComCliente(){
       Cliente c = funcionario.recuperarCliente(getCpfClienteVenda());
       funcionario.cadastrarVendaComCliente((Funcionario) Login.getInstance().getUsuario(), c);
   }

   public void DescontoGerente(){
       final String[] login = new String[1];
       final String[] senha = new String[1];

       TextInputDialog inputDialog = new TextInputDialog();
       inputDialog.setTitle("Autenticação do gerente");
       inputDialog.setContentText("Login: ");
       inputDialog.showAndWait().ifPresent(v -> login[0] = v);

       TextInputDialog inputDialog1 = new TextInputDialog();
       inputDialog1.setTitle("Autenticação do gerente");
       inputDialog1.setContentText("senha: ");
       inputDialog1.showAndWait().ifPresent(v -> senha[0] = v);

       try {
           if (Login.getInstance().verificarLogin(login[0], senha[0]) == 1){
               gerente.darDesconto(login[0], senha[0], getSubTotal(), 10);
           }

       } catch (UsuarioInvalidoException e) {
           e.printStackTrace();
       } catch (DescontoInvalidoException e) {
           e.printStackTrace();
       }


   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherCampos();
    }
}
