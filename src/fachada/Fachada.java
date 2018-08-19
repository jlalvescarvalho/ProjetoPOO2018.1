package fachada;

import execoes.*;
import negocio.*;
import negocio.entidade.*;

import java.util.ArrayList;
import java.util.Date;

public class Fachada implements IFachadaGerente, IFachadaFuncionario{


    public int verificarLogin(String login, String senha) throws UsuarioInvalidoException {
        return Login.getInstance().verificarLogin(login, senha);
    }
    public void logoff(){
        Login.getInstance().logoff();
    }

     /**
     * Metodos referentes a Funcionario
     */

    @Override
    public void cadastrarEstoque(Produto produto, int quantidade) {
        ItemEstoque itemEstoque = new ItemEstoque(quantidade, produto);
        NegocioEstoque.getInstace().cadastrarEstoque(itemEstoque);
    }

    @Override
    public void realizarEntradaEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException, ProdutoNaoExisteException, CodigoInvalidoException {
        NegocioEstoque.getInstace().realizarEntradaEstoque(codigo, quantidade);
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
    public void cadastrarCliente(String nome, String cpf, String rua, String bairro, String cep, String numero, String cidade) throws CPFApenasNumerosException, CPFTamanhoException, NomeInvalidoException, ClienteJaExiteException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Cliente cliente = new Cliente(nome,cpf,end);

        NegocioCliente.getInstace().cadastrar(cliente);
    }
    @Override
    public Cliente recuperarCliente(String cpf) throws CPFInvalidoException, CPFTamanhoException, CPFApenasNumerosException {
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
    public void cadastrarVendaComCliente(Funcionario funcionario, Cliente cliente, double desconto) {
        NegocioVenda.getInstace().cadastrarVendaComCliente(funcionario, cliente, desconto);
    }

    @Override
    public double calcularTotalVenda(){
        return NegocioVenda.getInstace().calcularTotalVenda();
    }

    @Override
    public void cadastrarVendaSemCliente(Funcionario funcionario) {
        NegocioVenda.getInstace().cadastrarVendaSemCliente(funcionario);
    }

    @Override
    public void adicionarItem(String codigoProduto, int quantidade) throws ProdutoNaoExisteException, CodigoInvalidoException, QuantidadeNaoDisponivelException {
        NegocioVenda.getInstace().adicionarItem(codigoProduto, quantidade);
    }

    @Override
    public void cadastrarProduto(String codigo, String descricao, double preco, String marca) throws DescricaoInvalidaException, TamanhoInvalidoException, ApenasNumerosException, CodigoInvalidoException, PrecoInvalidoException, ProdutoJaExisteException, ProdutoInvalidoException {
        Produto produto = new Produto(codigo, descricao,preco, marca);
        NegocioProduto.getInstance().cadastrar(produto);
    }

    @Override
    public Produto recuperarProduto(String codigo) throws CodigoInvalidoException, ProdutoNaoExisteException {
        return NegocioProduto.getInstance().recuperar(codigo);
    }

    @Override
    public void removerProduto(String codigo) throws CodigoInvalidoException, ProdutoNaoExisteException {
        NegocioProduto.getInstance().remover(codigo);
    }

    @Override
    public ArrayList<Produto> recuperarTodosProdutos() {
        return NegocioProduto.getInstance().recuperarTodos();
    }

    @Override
    public void atualizarProduto(String codigo, Produto produto) {
        NegocioProduto.getInstance().atualizar(codigo, produto);
    }

    @Override
    public ArrayList<ItemVenda> getListarItens(){
        return NegocioVenda.getInstace().listaItensdaVenda;
    }

    @Override
    public double verificarFrequencia(String cpf) throws CPFInvalidoException, CPFApenasNumerosException, CPFTamanhoException {
        return NegocioCliente.getInstace().verificarFrequencia(cpf);
    }

    @Override
    public void esvaziarListaVenda(){
        NegocioVenda.getInstace().esvaziarListaVendas();
    }


    /**
     * Apartir daqui metodos referentes a Gerente
    */

    @Override
    public void cadastrarFuncionario(String nome, String cpf, String rua, String bairro, String cep, String numero, String cidade,
                                     String senha)
            throws UsuarioJaExisteException, UsuarioInvalidoException, CPFApenasNumerosException, NomeInvalidoException, CPFTamanhoException, CPFInvalidoException, SenhaInvalidaException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario funcionario = new Funcionario(nome, cpf, end, SalarioCargoEnum.Funcionario.getSalario(), senha);
        NegocioUsuario.getInstace().cadastrar(funcionario);
    }

    @Override
    public void cadastrarGerente(String nome, String cpf, String rua, String bairro, String cep, String numero,
                                 String cidade, String senha, int numFuncGerenciados)
            throws UsuarioJaExisteException, UsuarioInvalidoException, CPFTamanhoException, NomeInvalidoException, CPFApenasNumerosException, CPFInvalidoException, SenhaInvalidaException {
        Endereco end = new Endereco(rua, numero, bairro, cep, cidade);
        Usuario gerente = new Gerente(nome, cpf, end, SalarioCargoEnum.Gerente.getSalario(), senha, numFuncGerenciados);
        NegocioUsuario.getInstace().cadastrar(gerente);
    }

    @Override
    public void promoverParaGerente(Usuario usuario) throws UsuarioJaExisteException, UsuarioInvalidoException, CPFApenasNumerosException, NomeInvalidoException, CPFTamanhoException, CPFInvalidoException, SenhaInvalidaException {
        removerUsuario(usuario.getCpf());

        cadastrarGerente(usuario.getNome(), usuario.getCpf(), usuario.getEndereco().getRua(),usuario.getEndereco().getBairro(),
                usuario.getEndereco().getCep(),usuario.getEndereco().getNumero(),usuario.getEndereco().getCidade(),usuario.getSenha(),5);
    }

    @Override
    public Usuario recuperarUsuario(String cpf){
        return NegocioUsuario.getInstace().recuperar(cpf);
    }

    @Override
    public void removerUsuario(String cpf) throws UsuarioInvalidoException {
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

    @Override
    public void removerItem(int id) throws ItemVendaInvalidoException {
        NegocioVenda.getInstace().removerItem(id);
    }

    @Override
    public double darDesconto(String cpfGerente, String senha, double valorVenda, double desconto) throws DescontoInvalidoException, UsuarioInvalidoException {
        return NegocioUsuario.getInstace().darDesconto(cpfGerente,senha,valorVenda,desconto);
    }


}
