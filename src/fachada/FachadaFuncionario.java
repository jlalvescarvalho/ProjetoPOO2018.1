package fachada;

import negocio.*;
import negocio.entidade.*;

import java.util.ArrayList;

public class FachadaFuncionario {


    public int verificarLogin(String login, String senha){
       return NegocioUsuario.getInstance().verificarLogin(login, senha);
    }

   public void cadastrarEstoque(Produto produto, int quantidade){
        ItemEstoque itemEstoque = new ItemEstoque(quantidade, produto);
        NegocioEstoque.getInstance().cadastrarEstoque(itemEstoque);
   }
   public void realizarEntradaEstoque(Produto produto, int quantidade){
        NegocioEstoque.getInstance().realizarEntradaEstoque(produto, quantidade);
   }
   public ItemEstoque recuperarItemEstoque(String codigoProduto){
        return NegocioEstoque.getInstance().recuperarItemEstoque(codigoProduto);
   }
   public ArrayList<ItemEstoque> recuperarEstoque(){
        return NegocioEstoque.getInstance().recuperarTudo();
   }
   public void removerItemEstoque(String codigoProduto){
        NegocioEstoque.getInstance().removerItemEstoque(codigoProduto);
   }
   public void realizarSaidaEstoque(Produto produto, int quantidade){
        NegocioEstoque.getInstance().realizarSaidaEstoque(produto, quantidade);
   }
   public void atualizarItemEstoque(String codigoProduto, ItemEstoque itemEstoque){
        NegocioEstoque.getInstance().atualizarEstoque(codigoProduto,itemEstoque);
   }
    //------------------------------------------------------------------------------------------------------
    //Cliente

    public void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade) {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Cliente cliente = new Cliente(nome,cpf,end);

        NegocioCliente.getInstance().cadastrar(cliente);
    }

    public Cliente recuperarCliente(String cpf) {
        return NegocioCliente.getInstance().recuperar(cpf);
    }

    public void removerCliente(Cliente cliente) { NegocioCliente.getInstance().remover(cliente); }

    public void atualizarCliente(String cpf, Cliente cliente) {
        NegocioCliente.getInstance().atualizar(cpf, cliente);
    }

    public ArrayList<Cliente> recuperarTodosCliente() {
        return NegocioCliente.getInstance().recuperarTodos();
    }

    //Vendas
    public void cadastrarVenda(Funcionario funcionario, Cliente cliente){
        NegocioVenda.getInstance().cadastrarVenda(funcionario, cliente);
    }
    public void adicionarItem(String codigoProduto, int quantidade){
        NegocioVenda.getInstance().adicionarItem(codigoProduto, quantidade);
    }

}
