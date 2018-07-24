package negocio;

import negocio.entidade.Cliente;
import repositorio.IRepositorio;
import repositorio.RepositorioCliente;

import javax.swing.*;
import java.util.ArrayList;

public class NegocioCliente {

    private IRepositorio repositorioCliente;
    private static NegocioCliente mySelf;

    public NegocioCliente() {
        repositorioCliente = new RepositorioCliente();
    }

    public static NegocioCliente getInstance() {
        if(mySelf == null){
            mySelf = new NegocioCliente();
        }
        return mySelf;
    }

    public void cadastrar(Cliente cliente){
        if (cliente != null){
            repositorioCliente.cadastrar(cliente);
        }
    }

    public Cliente recuperar(String cpf){
        return (Cliente) repositorioCliente.recuperar(cpf);
    }

    public ArrayList<Cliente> recuperarTodos(){
        return repositorioCliente.recupertarTudo();
    }

    public void remover(Cliente cliente){
        try {
            repositorioCliente.remover(cliente);
        }catch (NullPointerException ne){
            JOptionPane.showMessageDialog(null,ne.getMessage());
            ne.setStackTrace(ne.getStackTrace());
        }
    }

    public void atualizar(String cpf, Cliente clienteNew){
        repositorioCliente.atualizar(cpf, clienteNew);
    }

}
