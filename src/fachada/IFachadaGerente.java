package fachada;

import execoes.UsuarioInvalidoException;
import execoes.UsuarioJaExisteException;
import negocio.entidade.Cliente;
import negocio.entidade.Usuario;
import negocio.entidade.Venda;

import java.util.ArrayList;
import java.util.Date;

public interface IFachadaGerente {

    int verificarLogin(String login, String senha);
    void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, double salario, String senha) throws UsuarioJaExisteException, UsuarioInvalidoException;
    void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, double salario, String senha, int numFuncGerenciados) throws UsuarioJaExisteException, UsuarioInvalidoException;
    Usuario recuperarUsuario(String cpf);
    void removerUsuario(String cpf);
    void atualizarUsuario(String cpf, Usuario usuario);
    ArrayList<Usuario> recuperarTodosUsuarios();
    Venda recuperarVenda(String id);
    void removerVenda(String id);
    ArrayList<Venda> recuperarTodasVendas();
    ArrayList<Venda> gerarRelatorioPorDatas(Date dataInicial, Date dataFinal);



    }