package fachada;

import negocio.NegocioUsuario;
import negocio.entidade.*;

import java.util.ArrayList;

/**
 * @author Luciano/Giudicelli
 * FachadaGerente é uma ligação entre a interface e o codigo;
 */
public class FachadaGerente {

    private NegocioUsuario negocioUsuario;

    public static FachadaGerente mySelf;

    public FachadaGerente(){

        this.negocioUsuario = new NegocioUsuario();

    }

    public static FachadaGerente getInstance(){
        if (mySelf == null){
            mySelf = new FachadaGerente();
        }
        return mySelf;
    }

    //-----------------------------------------------
    // Funcionario
    public void cadastrarUsuario(String nome, String cpf, String rua, int numero, String cidade, String cargo, String senha) {

        Endereco end = new Endereco(rua, numero, cidade);
        Usuario usuFun = new Funcionario(nome, cpf, end, senha);
        negocioUsuario.getInstance().cadastrar(usuFun);
    }

    public Usuario recuperarUsuario(String cpf) { return negocioUsuario.getInstance().recuperar(cpf); }

    public void removerUsuario(String cpf) {
        Usuario usu = recuperarUsuario(cpf);
        negocioUsuario.getInstance().remover(usu);
    }

    public void atualizarUsuario(String cpf, Usuario usuario) {
        negocioUsuario.getInstance().atualizar(cpf, usuario);
    }

    public ArrayList<Usuario> recuperarTodosUsuarios() {
        return negocioUsuario.getInstance().recuperarTodos();
    }





}
