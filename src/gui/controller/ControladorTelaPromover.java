package gui.controller;

import execoes.*;
import fachada.Fachada;
import fachada.IFachadaGerente;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.entidade.Gerente;
import negocio.entidade.SalarioCargoEnum;
import negocio.entidade.Usuario;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorTelaPromover implements Initializable {

    IFachadaGerente gerente = new Fachada();

    private Stage tela;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelSalario;
    @FXML
    private TextField txtCpfPromover;
    @FXML
    private ComboBox<String> comboCargos;


    public void buscarFuncionario(){
        try {
            Usuario usuario = gerente.recuperarUsuario(txtCpfPromover.getText());

            labelNome.setText(usuario.getNome());
            labelSalario.setText(String.valueOf(usuario.getSalario()));

            preencherComboBox(usuario);

        } catch (UsuarioNaoExisteException e) {
            e.printStackTrace();
        }
    }

    private void preencherComboBox(Usuario usuario){
        if (usuario instanceof Gerente){
            comboCargos.setValue("");
        }else{
            comboCargos.setValue(SalarioCargoEnum.Gerente.name());
        }
    }

    public void promoverFuncionario(){
        try {
            Usuario usuario = gerente.recuperarUsuario(txtCpfPromover.getText());

            if (comboCargos.getValue().equals(SalarioCargoEnum.Gerente.name())){
                gerente.promoverParaGerente(usuario);
            }

        } catch (UsuarioNaoExisteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UsuarioInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UsuarioJaExisteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (CPFApenasNumerosException e) {
            e.printStackTrace();
        } catch (NomeInvalidoException e) {
            e.printStackTrace();
        } catch (CPFTamanhoException e) {
            e.printStackTrace();
        }

    }

    public void cancelar(ActionEvent actionEvent){
        tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        Main.chamarTela("view/TelaGerente.fxml", 600,400);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
