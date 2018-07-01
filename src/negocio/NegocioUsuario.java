package negocio;

import negocio.entidade.Usuario;
import repositorio.RepositorioUsuario;

import java.util.ArrayList;

public class NegocioUsuario {

    private RepositorioUsuario repositorioUsuario;
    public static NegocioUsuario mySelf;

    public NegocioUsuario(){
        this.repositorioUsuario = new RepositorioUsuario();
    }

    public static NegocioUsuario getInstance(){
        if (mySelf == null){
            mySelf = new NegocioUsuario();
        }
        return mySelf;
    }

    public void cadastrar(Usuario usuario){
        if(usuario != null){
            this.repositorioUsuario.cadastrar(usuario);
        }
    }

    public Usuario recuperar(long cpf){
        return this.repositorioUsuario.recuperar(cpf);
    }

    public void remover(Usuario usuario){
        this.repositorioUsuario.remover(usuario);
    }

    public void atualizar(long cpf, Usuario usuarioNew){
        this.repositorioUsuario.atualizar(cpf, usuarioNew);
    }

    public ArrayList<Usuario> recuperarTodos(){
        return this.repositorioUsuario.recuoertarTudo();
    }
}
