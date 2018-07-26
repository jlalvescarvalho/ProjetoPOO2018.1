package fachada;

import execoes.*;
import negocio.*;
import negocio.entidade.*;

import java.util.ArrayList;
import java.util.Date;

public class Fachada implements IFachadaGerente, IFachadaFuncionario{
     /**
     * Metodos referentes a Funcionario
     */

    @Override
    public void cadastrarEstoque(Produto produto, int quantidade) {
        ItemEstoque itemEstoque = new ItemEstoque(quantidade, produto);
        NegocioEstoque.getInstace().cadastrarEstoque(itemEstoque);
    }

    @Override
    public void realizarEntradaEstoque(Produto produto, int quantidade) {
        NegocioEstoque.getInstace().realizarEntradaEstoque(produto, quantidade);
    }

    @Override
    public ItemEstoque recuperarItemEstoque(String codigoProduto) {
        return NegocioEstoque.getInstace().recuperarItemEstoque(codigoProduto);
    }

    @Override
    public ArrayList<ItemEstoque> recuperarEstoque() {
        return NegocioEstoque.getInstace().recuperarTudo();
    }

    @Override
    public void removerItemEstoque(String codigoProduto) {
        NegocioEstoque.getInstace().removerItemEstoque(codigoProduto);
    }

    @Override
    public void realizarSaidaEstoque(Produto produto, int quantidade) {
        NegocioEstoque.getInstace().realizarSaidaEstoque(produto, quantidade);
    }

    @Override
    public void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade) throws CPFApenasNumerosException, CPFTamanhoException, NomeInvalidoException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Cliente cliente = new Cliente(nome,cpf,end);

        NegocioCliente.getInstace().cadastrar(cliente);
    }
    @Override
    public Cliente recuperarCliente(String cpf) {
        return NegocioCliente.getInstace().recuperar(cpf);
    }

    @Override
    public void removerCliente(Cliente cliente) {
        NegocioCliente.getInstace().remover(cliente);
    }

    @Override
    public void atualizarCliente(String cpf, Cliente cliente) {
        NegocioCliente.getInstace().atualizar(cpf, cliente);
    }

    @Override
    public ArrayList<Cliente> recuperarTodosCliente() {
        return NegocioCliente.getInstace().recuperarTodos();
    }

    @Override
    public void cadastrarVendaCliente(Funcionario funcionario, Cliente cliente) {
        NegocioVenda.getInstace().cadastrarVendaCliente(funcionario, cliente);
    }

    @Override
    public void cadastrarVendaSemCliente(Funcionario funcionario) {
        NegocioVenda.getInstace().cadastrarVendaNormal(funcionario);
    }

    @Override
    public void adicionarItem(String codigoProduto, int quantidade) {
        NegocioVenda.getInstace().adicionarItem(codigoProduto, quantidade);
    }

    @Override
    public void cadastrarProduto(String codigo, String descricao, double preco, String marca) {
        Produto produto = new Produto(codigo, descricao,preco, marca);
        NegocioProduto.getInstance().cadastrar(produto);
    }

    @Override
    public Produto recuperarProduto(String codigo) {
        return NegocioProduto.getInstance().recuperar(codigo);
    }

    @Override
    public void removerProduto(String codigo) {
        Produto p = recuperarProduto(codigo);
        NegocioProduto.getInstance().remover(p);
    }

    @Override
    public ArrayList<Produto> recuperarTodosProdutos() {
        return NegocioProduto.getInstance().recuperarTodos();
    }

    @Override
    public void atualizarProduto(String cpf, Produto produto) {
        NegocioProduto.getInstance().atualizar(cpf, produto);
    }

    /**
     * Apartir daqui metodos referentes a Gerente
    */

    @Override
    public int verificarLogin(String login, String senha) {
        return NegocioUsuario.getInstace().verificarLogin(login, senha);
    }

    @Override
    public void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, double salario, String senha) throws UsuarioJaExisteException, UsuarioInvalidoException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario funcionario = new Funcionario(nome, cpf, end, salario, senha);
        NegocioUsuario.getInstace().cadastrar(funcionario);
    }

    @Override
    public void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, int numero, String cidade, double salario, String senha, int numFuncGerenciados) throws UsuarioJaExisteException, UsuarioInvalidoException  {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario gerente = new Gerente(nome, cpf, end, salario, senha, numFuncGerenciados);
        NegocioUsuario.getInstace().cadastrar(gerente);
    }

    @Override
    public Usuario recuperarUsuario(String cpf) {
        return NegocioUsuario.getInstace().recuperar(cpf);
    }

    @Override
    public void removerUsuario(String cpf) {
        Usuario usu = recuperarUsuario(cpf);
        NegocioUsuario.getInstace().remover(usu);
    }

    @Override
    public void atualizarUsuario(String cpf, Usuario usuario) {
        NegocioUsuario.getInstace().atualizar(cpf, usuario);
    }

    @Override
    public ArrayList<Usuario> recuperarTodosUsuarios() {
        return NegocioUsuario.getInstace().recuperarTodos();
    }

    @Override
    public Venda recuperarVenda(String id) {
        return NegocioVenda.getInstace().recuperar(id);
    }

    @Override
    public void removerVenda(String id) {
        Venda venda = recuperarVenda(id);
        NegocioVenda.getInstace().remover(venda);
    }

    @Override
    public ArrayList<Venda> recuperarTodasVendas() {
        return NegocioVenda.getInstace().recuperarTodos();
    }

    @Override
    public ArrayList<Venda> gerarRelatorioPorDatas(Date dataInicial, Date dataFinal) {
        return NegocioVenda.getInstace().gerarRelatorioVendas(dataInicial, dataFinal);
    }


}
