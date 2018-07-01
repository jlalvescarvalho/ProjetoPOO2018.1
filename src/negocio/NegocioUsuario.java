package negocio;

import negocio.entidade.Usuario;
import repositorio.RepositorioUsuario;

public class NegocioUsuario {

    private RepositorioUsuario repositorioUsuario;
    public static NegocioUsuario mySelf;

    public NegocioUsuario(){
        this.repositorioUsuario = new RepositorioUsuario();
    }

    public static NegocioUsuario getInstace(){
        if (mySelf == null){
            mySelf = new NegocioUsuario();
        }
        return mySelf;
    }

    public void cadastrar(Usuario usuario){
        if(usuario != null){

        }
    }
}
