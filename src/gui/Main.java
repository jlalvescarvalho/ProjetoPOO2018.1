package gui;
import fachada.FachadaFuncionario;
import fachada.FachadaGerente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.entidade.Gerente;
import negocio.entidade.Usuario;

import java.io.IOException;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("telalogin.fxml"));
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

        }

    }
    public static void main (String[] args){
        FachadaFuncionario ffun = new FachadaFuncionario();
        FachadaGerente fger = new FachadaGerente();

        fger.cadastrarFuncionario("Luciano", "0000", "Aqui", "centro","00000", 0,"Não interessa","0000");
        fger.cadastrarGerente("Gil", "123", "centro","centro","09090",0,"ai dentro", "0000");

        launch(args);
    }



}
