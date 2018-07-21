package negocio;

import negocio.entidade.Funcionario;
import negocio.entidade.Gerente;
import negocio.entidade.Usuario;
import repositorio.RepositorioUsuario;

import java.util.ArrayList;

public class NegocioUsuario {

    private RepositorioUsuario repositorioUsuario;
    private static NegocioUsuario mySelf;

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

    public Usuario recuperar(String cpf){
        return this.repositorioUsuario.recuperar(cpf);
    }

    public void remover(Usuario usuario){
        this.repositorioUsuario.remover(usuario);
    }

    public void atualizar(String cpf, Usuario usuarioNew){
        this.repositorioUsuario.atualizar(cpf, usuarioNew);
    }

    public ArrayList<Usuario> recuperarTodos(){
        return this.repositorioUsuario.recupertarTudo();
    }

    public int verificarLogin(String cpf, String senha) {
        final int funcionario = 0;
        final int gerente = 1;

        Usuario usuario = recuperar(cpf);
        if (usuario instanceof Funcionario && usuario.getSenha().equals(senha)){
            return funcionario;
        }else if(usuario instanceof Gerente && usuario.getSenha().equals(senha)){
            return gerente;
        }else{
            return -1;
        }
    }
}
