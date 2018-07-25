package fachada;

import negocio.entidade.Cliente;
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
    void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade);
    Cliente recuperarCliente(String cpf);
    }
