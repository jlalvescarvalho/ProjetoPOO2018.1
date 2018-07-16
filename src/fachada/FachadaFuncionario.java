package fachada;

import negocio.NegocioCliente;
import negocio.NegocioProduto;
import negocio.NegocioUsuario;
import negocio.NegocioVenda;
import negocio.entidade.Cliente;
import negocio.entidade.Endereco;
import negocio.entidade.Produto;
import negocio.entidade.Venda;

import java.util.ArrayList;

public class FachadaFuncionario {

    private NegocioProduto negocioProduto;
    private NegocioCliente negocioCliente;
    private NegocioUsuario negocioUsuario;
    private NegocioVenda negocioVenda;

    public static FachadaGerente mySelf;

    public FachadaFuncionario(){
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

    //Produto
    public void cadastrarProduto(Produto produto) {
        negocioProduto.getInstance().cadastrar(produto);
    }

    public Produto recuperarProduto(long id) {
        return negocioProduto.getInstance().recuperar(id);
    }

    public void removerProduto(Produto produto) { negocioProduto.getInstance().remover(produto); }

    public void atualizarProduto(long id, Produto produto) {
        negocioProduto.getInstance().atualizar(id, produto);
    }

    public ArrayList<Produto> recuperarTodosProdutos() {
        return negocioProduto.getInstance().recuperarTodos();
    }

    
    //--------------------------------------------------------
    //Venda
    public void cadastrarVenda(Venda venda) { negocioVenda.getInstance().cadastrar(venda); }

    public Venda recuperarVenda(long id) { return negocioVenda.getInstance().recuperar(id); }

    public void removerVenda(Long id) {
        Venda venda = recuperarVenda(id);
        negocioVenda.getInstance().remover(venda);
    }

    public void atualizarVenda(long id, Venda venda) {
        negocioVenda.getInstance().atualizar(id, venda);
    }

    public ArrayList<Venda> recuperarTodasVendas() {
        return negocioVenda.getInstance().recuperarTodos();
    }

    //------------------------------------------------------------------------------------------------------
    //Cliente

    public void cadastrarCliente(String nome, String cpf, char genero, String rua, int numero, String cidade) {
        Endereco end = new Endereco(rua, numero, cidade);
        Cliente cliente = new Cliente(nome,cpf,genero,end);

        negocioCliente.getInstance().cadastrar(cliente);
    }

    public Cliente recuperarCliente(long cpf) {
        return negocioCliente.getInstance().recuperar(cpf);
    }

    public void removerCliente(Cliente cliente) { negocioCliente.getInstance().remover(cliente); }

    public void atualizarProduto(long cpf, Cliente cliente) {
        negocioCliente.getInstance().atualizar(cpf, cliente);
    }

    public ArrayList<Cliente> recuperarTodosCliente() {
        return negocioCliente.getInstance().recuperarTodos();
    }

}
