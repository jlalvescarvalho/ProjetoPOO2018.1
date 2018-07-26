package fachada;

import execoes.CPFApenasNumerosException;
import execoes.CPFTamanhoException;
import execoes.NomeInvalidoException;
import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.entidade.ItemEstoque;
import negocio.entidade.Produto;

import java.util.ArrayList;

public interface IFachadaFuncionario {

    void cadastrarEstoque(Produto produto, int quantidade);
    void realizarEntradaEstoque(Produto produto, int quantidade);
    ItemEstoque recuperarItemEstoque(String codigoProduto);
    ArrayList<ItemEstoque> recuperarEstoque();
    void removerItemEstoque(String codigoProduto);
    void realizarSaidaEstoque(Produto produto, int quantidade);
    void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade) throws CPFApenasNumerosException, CPFTamanhoException, NomeInvalidoException;
    Cliente recuperarCliente(String cpf);
    void atualizarCliente(String cpf, Cliente cliente);
    void removerCliente(Cliente cliente);
    ArrayList<Cliente> recuperarTodosCliente();
    void cadastrarVendaCliente(Funcionario funcionario, Cliente cliente);
    void cadastrarVendaSemCliente(Funcionario funcionario);
    void adicionarItem(String codigoProduto, int quantidade);
    void cadastrarProduto(String codigo, String descricao, double preco, String marca);
    Produto recuperarProduto(String codigo);
    void removerProduto(String codigo);
    ArrayList<Produto> recuperarTodosProdutos();
    void atualizarProduto(String codigo, Produto produto);
    }
