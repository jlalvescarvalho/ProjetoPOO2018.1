package negocio;

import execoes.CPFApenasNumerosException;
import execoes.CPFInvalidoException;
import execoes.CPFTamanhoException;
import execoes.ClienteJaExiteException;
import javafx.scene.control.Alert;
import negocio.entidade.Cliente;
import repositorio.IRepositorio;
import repositorio.RepositorioCliente;

import javax.swing.*;
import java.util.ArrayList;

public class NegocioCliente {

    private IRepositorio repositorioCliente;
    private static NegocioCliente mySelf;

    private NegocioCliente() {
        repositorioCliente = new RepositorioCliente();
    }

    public static NegocioCliente getInstace(){
        if(mySelf == null){
            mySelf = new NegocioCliente();
        }
        return mySelf;
    }

    public static void verificarCpf(String cpf) throws CPFApenasNumerosException, CPFTamanhoException {
        char[] cpfChar = cpf.toCharArray();
        for(int i = 0; i < cpfChar.length; i++){
            if (!Character.isDigit(cpfChar[i])){
                throw new CPFApenasNumerosException();
            }
        }
        if (cpf.length() != 11){
            throw new CPFTamanhoException(cpf.length());
        }
    }

    public double verificarFrequencia(String cpf) throws CPFInvalidoException, CPFTamanhoException, CPFApenasNumerosException {
        Cliente cliente = recuperar(cpf);
        if (cliente.getFrequencia() == 10){
            return 0.10;
        }
        return 0.0;
    }

    public void cadastrar(Cliente cliente) throws ClienteJaExiteException {
        if (cliente != null && verificarSeExiste(cliente.getCpf())){
            repositorioCliente.cadastrar(cliente);
        }
    }

    private boolean verificarSeExiste(String cpf) throws ClienteJaExiteException {
        if ((Cliente)repositorioCliente.recuperar(cpf) == null){
            return true;
        }else {
            throw new ClienteJaExiteException();
        }
    }


    public Cliente recuperar(String cpf) throws CPFInvalidoException, CPFApenasNumerosException, CPFTamanhoException {
        if (cpf.equals("") || cpf.equals(" ")){
            throw new CPFInvalidoException();
        }else{
            verificarCpf(cpf);
            return (Cliente) repositorioCliente.recuperar(cpf);
        }

    }

    public ArrayList<Cliente> recuperarTodos(){
        return repositorioCliente.recupertarTudo();
    }

    public void remover(Cliente cliente){
        try {
            repositorioCliente.remover(cliente);
        }catch (NullPointerException ne){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencao");
            alert.setHeaderText(ne.getMessage());
            alert.showAndWait();
        }
    }

    public void atualizar(String cpf, Cliente clienteNew){
        repositorioCliente.atualizar(cpf, clienteNew);
    }

}
