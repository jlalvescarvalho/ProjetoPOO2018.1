package gui;
import execoes.UsuarioInvalidoException;
import execoes.UsuarioJaExisteException;
import fachada.Fachada;
import fachada.IFachadaGerente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            gerente.cadastrarFuncionario("Luciano", "0000", "Aqui", "centro","00000", 0,"Não interessa",500.00,"0000");
        } catch (UsuarioJaExisteException e) {
            e.printStackTrace();
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
        }
        try {
            gerente.cadastrarGerente("Gil", "123", "centro","centro","09090",0,"ai dentro", 2000.00,"0000",8);
        } catch (UsuarioJaExisteException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        launch(args);
    }



}
