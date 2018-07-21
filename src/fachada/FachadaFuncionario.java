package fachada;

import negocio.*;
import negocio.entidade.*;

import java.util.ArrayList;

public class FachadaFuncionario {

    private NegocioProduto negocioProduto;
    private NegocioCliente negocioCliente;
    private NegocioVenda negocioVenda;
    private NegocioEstoque negocioEstoque;
    private NegocioUsuario negocioUsuario;


    public int verificarLogin(String login, String senha){
       return negocioUsuario.verificarLogin(login, senha);
    }
    //Estoque
    public void cadastrarEstoque(String nome, ArrayList<ItemEstoque> itemEstoque){
        this.negocioEstoque.getInstance().cadastrarEstoque(nome, itemEstoque);
    }
    public Estoque recuperarEstoque(String nome){
       return this.negocioEstoque.getInstance().recuperarEstoque(nome);
    }

    public void removerEstoque(String nome){
        this.negocioEstoque.removerEstoque(nome);
    }

    public void atualizarEstoque(String nome, Estoque estoque){
        this.negocioEstoque.atualizarEstoque(nome, estoque);
    }

    public ArrayList<Estoque> recuperarTudo(){
        return this.negocioEstoque.recuperarTudo();
    }
    //---------------------------------------------------
    //Produto
    public void cadastrarProduto(Produto produto) {
        negocioProduto.getInstance().cadastrar(produto);
    }

    public Produto recuperarProduto(String codigo) {
        return negocioProduto.getInstance().recuperar(codigo);
    }

    public void removerProduto(Produto produto) { negocioProduto.getInstance().remover(produto); }

    public void atualizarProduto(String codigo, Produto produto) {
        negocioProduto.getInstance().atualizar(codigo, produto);
    }

    public ArrayList<Produto> recuperarTodosProdutos() {
        return negocioProduto.getInstance().recuperarTodos();
    }


    //--------------------------------------------------------
    //Venda
    public void cadastrarVenda(Venda venda) { negocioVenda.getInstance().cadastrar(venda); }

    public void atualizarVenda(String id, Venda venda) {
        negocioVenda.getInstance().atualizar(id, venda);
    }

    public ArrayList<Venda> recuperarTodasVendas() {
        return negocioVenda.getInstance().recuperarTodos();
    }

    //------------------------------------------------------------------------------------------------------
    //Cliente

    public void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade) {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Cliente cliente = new Cliente(nome,cpf,end);

        negocioCliente.getInstance().cadastrar(cliente);
    }

    public Cliente recuperarCliente(String cpf) {
        return negocioCliente.getInstance().recuperar(cpf);
    }

    public void removerCliente(Cliente cliente) { negocioCliente.getInstance().remover(cliente); }

    public void atualizarProduto(String cpf, Cliente cliente) {
        negocioCliente.getInstance().atualizar(cpf, cliente);
    }

    public ArrayList<Cliente> recuperarTodosCliente() {
        return negocioCliente.getInstance().recuperarTodos();
    }

}
