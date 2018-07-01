package negocio;

import negocio.entidade.Cliente;
import repositorio.RepositorioCliente;

import java.util.ArrayList;

public class NegocioCliente {

    private RepositorioCliente repositorioCliente;
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

    public void Cadastrar(Cliente cliente){
        if (cliente != null){
            repositorioCliente.cadastrar(cliente);
        }
    }

    public Cliente recuperar(long cpf){
        return repositorioCliente.recuperar(cpf);
    }

    public ArrayList<Cliente> recuperarTodos(){
        return repositorioCliente.recuoertarTudo();
    }

    public void remover(Cliente cliente){
        repositorioCliente.remover(cliente);
    }

    public void atualizar(long cpf, Cliente clienteNew){
        repositorioCliente.atualizar(cpf, clienteNew);
    }

}
