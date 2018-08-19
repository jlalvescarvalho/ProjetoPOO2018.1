package fachada;

import execoes.*;
import negocio.entidade.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public interface IFachadaFuncionario {

    void cadastrarEstoque(Produto produto, int quantidade);
    void realizarEntradaEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException, ProdutoNaoExisteException, CodigoInvalidoException;
    ItemEstoque recuperarItemEstoque(String codigoProduto);
    void esvaziarListaVenda();
    ArrayList<ItemEstoque> recuperarEstoque();
    void removerItemEstoque(String codigoProduto);
    void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, String numero, String cidade) throws CPFApenasNumerosException, CPFTamanhoException, NomeInvalidoException, ClienteJaExiteException;
    Cliente recuperarCliente(String cpf) throws CPFInvalidoException, CPFTamanhoException, CPFApenasNumerosException;
    void atualizarCliente(String cpf, Cliente cliente);
    void removerCliente(Cliente cliente);
    ArrayList<Cliente> recuperarTodosCliente();
    void cadastrarVendaComCliente(Funcionario funcionario, Cliente cliente, double desconto);
    void cadastrarVendaSemCliente(Funcionario funcionario);
    void adicionarItem(String codigoProduto, int quantidade) throws ProdutoNaoExisteException, CodigoInvalidoException, QuantidadeNaoDisponivelException;
    void cadastrarProduto(String codigo, String descricao, double preco, String marca) throws CodigoInvalidoException, DescricaoInvalidaException, TamanhoInvalidoException, ApenasNumerosException, PrecoInvalidoException, ProdutoJaExisteException, ProdutoInvalidoException;
    Produto recuperarProduto(String codigo) throws CodigoInvalidoException, ProdutoNaoExisteException;
    void removerProduto(String codigo) throws CodigoInvalidoException, ProdutoNaoExisteException;
    ArrayList<Produto> recuperarTodosProdutos();
    void atualizarProduto(String codigo, Produto produto);
    ArrayList<ItemVenda> getListarItens();
    double verificarFrequencia(String cpf) throws CPFInvalidoException, CPFApenasNumerosException, CPFTamanhoException;
    double calcularTotalVenda();
    }
