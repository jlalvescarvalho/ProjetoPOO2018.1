package fachada;

import negocio.NegocioCliente;
import negocio.NegocioUsuario;
import negocio.NegocioVenda;
import negocio.entidade.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Luciano/Giudicelli
 * FachadaGerente é uma ligação entre a interface e o codigo;
 */
public class FachadaGerente {

    private NegocioUsuario negocioUsuario;
    private NegocioVenda negocioVenda;
    private NegocioCliente negocioCliente;

    public FachadaGerente(){

        this.negocioUsuario = new NegocioUsuario();
        this.negocioVenda = new NegocioVenda();
        this.negocioCliente = new NegocioCliente();

    }

    //-----------------------------------------------
    // Funcionario
    public void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, String senha) {

        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario funcionario = new Funcionario(nome, cpf, end, senha);
        negocioUsuario.getInstance().cadastrar(funcionario);
    }
    public void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, String senha){
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario gerente = new Gerente(nome, cpf, end, senha);
        negocioUsuario.getInstance().cadastrar(gerente);
    }

    public Usuario recuperarUsuario(String cpf) {
        return negocioUsuario.getInstance().recuperar(cpf); }

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


    //Venda

    public Venda recuperarVenda(long id) {
        return negocioVenda.getInstance().recuperar(id);
    }

    public void removerVenda(Long id) {

        Venda venda = recuperarVenda(id);
        negocioVenda.getInstance().remover(venda);
    }



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
