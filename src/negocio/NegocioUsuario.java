package negocio;

import execoes.*;
import negocio.entidade.Funcionario;
import negocio.entidade.Gerente;
import negocio.entidade.Usuario;
import repositorio.IRepositorio;
import repositorio.RepositorioUsuario;

import java.util.ArrayList;

public class NegocioUsuario {

    private IRepositorio repositorioUsuario;
    private static NegocioUsuario mySelf;



    private NegocioUsuario(){
        this.repositorioUsuario = new RepositorioUsuario();
    }
    public static NegocioUsuario getInstace(){
        if(mySelf == null){
            mySelf = new NegocioUsuario();
        }
        return mySelf;
    }


    public void cadastrar(Usuario usuario) throws UsuarioJaExisteException, UsuarioInvalidoException, CPFInvalidoException {

        if(usuario.getCpf().equals("") || usuario.getNome().equals("")){
            throw new UsuarioInvalidoException();
        }else if(recuperar(usuario.getCpf()) != null) {
            throw new UsuarioJaExisteException();
        }else{
            this.repositorioUsuario.cadastrar(usuario);
        }
    }

    public Usuario recuperar(String cpf) {
        if(this.repositorioUsuario.recuperar(cpf) != null){
            return (Usuario) this.repositorioUsuario.recuperar(cpf);
        }
        return null;
    }

    public void remover(Usuario usuario) throws UsuarioInvalidoException{
        if (usuario == null){
            throw new UsuarioInvalidoException();
        }else{
            this.repositorioUsuario.remover(usuario);
        }

    }

    public void atualizar(String cpf, Usuario usuarioNew){
        this.repositorioUsuario.atualizar(cpf, usuarioNew);
    }

    public ArrayList<Usuario> recuperarTodos(){
        return this.repositorioUsuario.recupertarTudo();
    }



    public double darDesconto(String cpfGerente, String senha, double valorVenda, double desconto) throws DescontoInvalidoException, UsuarioInvalidoException {
        Gerente gerente = (Gerente) repositorioUsuario.recuperar(cpfGerente);
        if(gerente.getSenha().equals(senha)) {
            if (valorVenda > 100 && desconto > 0) {
                return gerente.darDesconto(desconto);
            } else {
                throw new DescontoInvalidoException(desconto);
            }
        }else{
           throw new UsuarioInvalidoException();
        }
    }
}
