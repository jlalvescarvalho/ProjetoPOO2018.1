package fachada;

import execoes.*;
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


    // Funcionario
    public void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, String senha) throws UsuarioJaExisteException, UsuarioInvalidoException {

        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario funcionario = new Funcionario(nome, cpf, end, senha);
        NegocioUsuario.getInstance().cadastrar(funcionario);
    }
    public void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, String senha) throws UsuarioJaExisteException, UsuarioInvalidoException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario gerente = new Gerente(nome, cpf, end, senha);
        NegocioUsuario.getInstance().cadastrar(gerente);
    }

    public Usuario recuperarUsuario(String cpf) {
        return NegocioUsuario.getInstance().recuperar(cpf); }

    public void removerUsuario(String cpf) {
        Usuario usu = recuperarUsuario(cpf);
        NegocioUsuario.getInstance().remover(usu);
    }

    public void atualizarUsuario(String cpf, Usuario usuario) {
        NegocioUsuario.getInstance().atualizar(cpf, usuario);
    }

    public ArrayList<Usuario> recuperarTodosUsuarios() {
        return NegocioUsuario.getInstance().recuperarTodos();
    }


    //Venda

    public Venda recuperarVenda(String id) {
        return NegocioVenda.getInstance().recuperar(id);
    }

    public void removerVenda(String id) {
        Venda venda = recuperarVenda(id);
        NegocioVenda.getInstance().remover(venda);
    }
    public ArrayList<Venda> recuperarTodasVendas(){
        return NegocioVenda.getInstance().recuperarTodos();
    }



    //Cliente

    public void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade) throws CPFTamanhoException, NomeInvalidoException, CPFApenasNumerosException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Cliente cliente = new Cliente(nome,cpf,end);

        NegocioCliente.getInstance().cadastrar(cliente);
    }

    public Cliente recuperarCliente(String cpf) {
        return NegocioCliente.getInstance().recuperar(cpf);
    }

    public void removerCliente(Cliente cliente) { NegocioCliente.getInstance().remover(cliente); }

    public ArrayList<Cliente> recuperarTodosCliente() {
        return NegocioCliente.getInstance().recuperarTodos();
    }


}
