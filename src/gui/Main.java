package gui;
import execoes.*;
import fachada.Fachada;
import fachada.IFachadaGerente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/TelaLogin.fxml"));
        primaryStage.setTitle("Mercado");
        primaryStage.setScene(new Scene(root,600,434));
        primaryStage.show();
    }

    public static void chamarTela(String nomeDaTela, int largura, int altura) {
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource(nomeDaTela));
            stage.setTitle("Mercado");
            stage.setScene(new Scene(root, largura, altura));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void main (String[] args){
        IFachadaGerente gerente = new Fachada();

        try {
            gerente.cadastrarGerente("Admin", "11122233345", "centro","centro","09090","0","ai dentro","0000",8);
        } catch (UsuarioJaExisteException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (UsuarioInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CPFApenasNumerosException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (CPFTamanhoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (NomeInvalidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

        launch(args);
    }



}
