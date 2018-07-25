package fachada;

import negocio.entidade.Cliente;
import negocio.entidade.Usuario;
import negocio.entidade.Venda;

import java.util.ArrayList;

public interface IFachadaGerente {

    int verificarLogin(String login, String senha);
    void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, String senha);
    void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, String senha);
    Usuario recuperarUsuario(String cpf);
    void removerUsuario(String cpf);
    void atualizarUsuario(String cpf, Usuario usuario);
    ArrayList<Usuario> recuperarTodosUsuarios();
    Usuario recuperarVenda(String id);
    void removerVenda(String id);
    ArrayList<Venda> recuperarTodasVendas();
    void cadastrarCliente();
    Cliente recuperarCliente(String cpf);
    void removerCliente(Cliente cliente);
    ArrayList<Cliente> recuperarTodosCliente();


    }