package fachada;

import execoes.*;
import negocio.entidade.Cliente;
import negocio.entidade.SalarioCargoEnum;
import negocio.entidade.Usuario;
import negocio.entidade.Venda;

import java.util.ArrayList;
import java.util.Date;

public interface IFachadaGerente {


    void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, String numero, String cidade, String senha) throws UsuarioJaExisteException, UsuarioInvalidoException, CPFApenasNumerosException, NomeInvalidoException, CPFTamanhoException, CPFInvalidoException;
    void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, String numero, String cidade, String senha, int numFuncGerenciados) throws UsuarioJaExisteException, UsuarioInvalidoException, CPFTamanhoException, NomeInvalidoException, CPFApenasNumerosException, CPFInvalidoException;
    Usuario recuperarUsuario(String cpf) throws UsuarioNaoExisteException;
    void removerUsuario(String cpf) throws UsuarioNaoExisteException, UsuarioInvalidoException;
    void atualizarUsuario(String cpf, Usuario usuario);
    ArrayList<Usuario> recuperarTodosUsuarios();
    Venda recuperarVenda(String id);
    void removerVenda(String id);
    ArrayList<Venda> recuperarTodasVendas();
    ArrayList<Venda> gerarRelatorioPorDatas(Date dataInicial, Date dataFinal);
    double darDesconto(String cpfGerente, String senha, double valorVenda, double desconto) throws DescontoInvalidoException, UsuarioInvalidoException;
    void promoverParaGerente(Usuario usuario) throws UsuarioNaoExisteException, UsuarioJaExisteException, UsuarioInvalidoException, CPFApenasNumerosException, NomeInvalidoException, CPFTamanhoException, CPFInvalidoException;


    }