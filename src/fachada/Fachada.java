package fachada;

import negocio.NegocioCliente;
import negocio.NegocioProduto;
import negocio.NegocioUsuario;
import negocio.NegocioVenda;
import negocio.entidade.Cliente;
import negocio.entidade.Produto;
import negocio.entidade.Usuario;
import negocio.entidade.Venda;

import java.util.ArrayList;

public class Fachada {

    private NegocioProduto negocioProduto;
    private NegocioCliente negocioCliente;
    private NegocioUsuario negocioUsuario;
    private NegocioVenda negocioVenda;

    public static Fachada mySelf;

    public Fachada(){
        this.negocioProduto = new NegocioProduto();
        this.negocioCliente = new NegocioCliente();
        this.negocioUsuario = new NegocioUsuario();
        this.negocioVenda = new NegocioVenda();
    }

    public static Fachada getInstance(){
        if (mySelf == null){
            mySelf = new Fachada();
        }
        return mySelf;
    }

    //Produto
    public void cadastrarProduto(Produto produto) {
        negocioProduto.getInstance().cadastrar(produto);
    }

    public Produto recuperarProduto(long id) {
        return negocioProduto.getInstance().recuperar(id);
    }

    public void removerProduto(Produto produto) {
       negocioProduto.getInstance().remover(produto);
    }

    public void atualizarProduto(long id, Produto produto) {
        negocioProduto.getInstance().atualizar(id, produto);
    }

    public ArrayList<Produto> recupertarTudosProdutos() {
        return negocioProduto.getInstance().recuperarTodos();
    }

    //-----------------------------------------
    //Cliente

    public void cadastrarCliente(Cliente cliente) {
        negocioCliente.getInstance().cadastrar(cliente);
    }

    public Cliente recuperarCliente(long cpf) {
        return negocioCliente.getInstance().recuperar(cpf);
    }

    public void removerCliente(Cliente cliente) {
        negocioCliente.getInstance().remover(cliente);
    }

    public void atualizarProduto(long cpf, Cliente cliente) {
        negocioCliente.getInstance().atualizar(cpf, cliente);
    }

    public ArrayList<Cliente> recuoertarTudosCliente() {
        return negocioCliente.getInstance().recuperarTodos();
    }

    //-----------------------------------------------
    // Usuario

    public void cadastrarUsuario(Usuario usuario) {
        negocioUsuario.getInstance().cadastrar(usuario);
    }

    public Usuario recuperarUsuario(long cpf) {
        return negocioUsuario.getInstance().recuperar(cpf);
    }

    public void removerUsuario(Usuario usuario) {
        negocioUsuario.getInstance().remover(usuario);
    }

    public void atualizarUsuario(long cpf, Usuario usuario) {
        negocioUsuario.getInstance().atualizar(cpf, usuario);
    }

    public ArrayList<Usuario> recuoertarTudosUsuarios() {
        return negocioUsuario.getInstance().recuperarTodos();
    }

    //--------------------------------------------------------
    //Venda

    public void cadastrarVenda(Venda venda) {
        negocioVenda.getInstance().cadastrar(venda);
    }

    public Venda recuperarVenda(long id) {
        return negocioVenda.getInstance().recuperar(id);
    }

    public void removerVenda(Venda venda) {
        negocioVenda.getInstance().remover(venda);
    }

    public void atualizarVenda(long id, Venda venda) {
        negocioVenda.getInstance().atualizar(id, venda);
    }

    public ArrayList<Venda> recuoertarTudasVendas() {
        return negocioVenda.getInstance().recuperarTodos();
    }

}
