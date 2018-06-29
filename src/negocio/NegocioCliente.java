package negocio;

import negocio.entidade.Cliente;
import repositorio.RepositorioCliente;

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

    }

}
