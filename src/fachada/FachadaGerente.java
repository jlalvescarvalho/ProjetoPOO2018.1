package fachada;

import negocio.NegocioCliente;
import negocio.NegocioProduto;
import negocio.NegocioUsuario;
import negocio.NegocioVenda;
import negocio.entidade.*;

import java.util.ArrayList;

/**
 * @author Luciano/Giudicelli
 * FachadaGerente é uma ligação entre a interface e o codigo;
 */
public class FachadaGerente {

    private NegocioProduto negocioProduto;
    private NegocioCliente negocioCliente;
    private NegocioUsuario negocioUsuario;
    private NegocioVenda negocioVenda;

    public static FachadaGerente mySelf;

    public FachadaGerente(){
        this.negocioProduto = new NegocioProduto();
        this.negocioCliente = new NegocioCliente();
        this.negocioUsuario = new NegocioUsuario();
        this.negocioVenda = new NegocioVenda();
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

    public Usuario recuperarUsuario(long cpf) { return negocioUsuario.getInstance().recuperar(cpf); }

    public void removerUsuario(long cpf) {
        Usuario usu = recuperarUsuario(cpf);
        negocioUsuario.getInstance().remover(usu);
    }

    public void atualizarUsuario(long cpf, Usuario usuario) {
        negocioUsuario.getInstance().atualizar(cpf, usuario);
    }

    public ArrayList<Usuario> recuperarTodosUsuarios() {
        return negocioUsuario.getInstance().recuperarTodos();
    }





}
