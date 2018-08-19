package negocio;

import execoes.CodigoInvalidoException;
import execoes.ProdutoNaoExisteException;
import execoes.QuantidadeNaoDisponivelException;
import negocio.entidade.*;
import repositorio.RepositorioVenda;

import java.util.ArrayList;
import java.util.Date;

public class NegocioVenda {

    private RepositorioVenda repositorioVenda;
    public ArrayList<ItemVenda> listaItensdaVenda;
    private static NegocioVenda mySelf;


    private NegocioVenda(){
        this.listaItensdaVenda = new ArrayList<>();
        this.repositorioVenda = new RepositorioVenda();

    }

    public static NegocioVenda getInstace(){
        if (mySelf == null){
            mySelf = new NegocioVenda();
        }
        return mySelf;
    }

    public void adicionarItem(String codigoProduto, int quantidade) throws ProdutoNaoExisteException, CodigoInvalidoException, QuantidadeNaoDisponivelException {
        Produto produto = recuperarProduto(codigoProduto);

        if (produto != null && verificarDisponibilidade(produto) >= quantidade) {
            if (!verificarSeItemJaExiste(produto.getCodigo())) {
                ItemVenda itemVenda = new ItemVenda(produto, quantidade);
                listaItensdaVenda.add(itemVenda);
            } else {
                for (int i = 0; i < listaItensdaVenda.size(); i++) {
                    if (listaItensdaVenda.get(i).getProduto().getCodigo().equals(codigoProduto)) {
                        if (verificarDisponibilidade(produto) >= listaItensdaVenda.get(i).getQuantidade()+quantidade) {
                            listaItensdaVenda.get(i).setQuantidade(quantidade);
                        } else {
                            throw new QuantidadeNaoDisponivelException(quantidade, verificarDisponibilidade(produto));
                        }
                    }
                }
            }

        } else {
            throw new QuantidadeNaoDisponivelException(quantidade, verificarDisponibilidade(produto));
        }
    }


    private boolean verificarSeItemJaExiste(String codigo){
        for (ItemVenda iv: this.listaItensdaVenda){
            if (iv.getProduto().getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }

    private Produto recuperarProduto(String codigo) throws ProdutoNaoExisteException, CodigoInvalidoException {
        return NegocioProduto.getInstance().recuperar(codigo);
    }


    private int verificarDisponibilidade(Produto produto){
        ItemEstoque item = NegocioEstoque.getInstace().recuperarItemEstoque(produto.getCodigo());
        if(item != null){
           return item.getQuantidade();
        }
        return 0;
    }

    public ArrayList<Venda> gerarRelatorioVendas(Date data1, Date data2){
        ArrayList<Venda> todasAsVendas = repositorioVenda.recupertarTudo();
        ArrayList<Venda> vendasSelecionadas = new ArrayList<>();

        for(int i = 0; i < todasAsVendas.size(); i++){
            if (todasAsVendas.get(i).getData().compareTo(data1) >= 0 && todasAsVendas.get(i).getData().compareTo(data2) <= 0){
                vendasSelecionadas.add(todasAsVendas.get(i));
            }
        }
        return vendasSelecionadas;
    }

    public double calcularTotalVenda(){
        double total = 0.0;

        for (ItemVenda iv: listaItensdaVenda){
            total += iv.getTotalItem();
        }
        return total;
    }


    public void cadastrarVendaComCliente(Funcionario funcionario, Cliente cliente, double desconto){
        cliente.incrementarFrequencia();
        Venda venda = new Venda(this.listaItensdaVenda, funcionario, cliente, desconto);
        this.repositorioVenda.cadastrar(venda);

        for(ItemVenda iv: listaItensdaVenda){
            NegocioEstoque.getInstace().realizarSaidaEstoque(iv.getProduto(), iv.getQuantidade());
        }
        this.listaItensdaVenda = new ArrayList<>();
    }

    public void cadastrarVendaSemCliente(Funcionario funcionario){
        Venda venda = new Venda(this.listaItensdaVenda, funcionario);
        this.repositorioVenda.cadastrar(venda);

        for(ItemVenda iv: listaItensdaVenda){
            NegocioEstoque.getInstace().realizarSaidaEstoque(iv.getProduto(), iv.getQuantidade());
        }
        this.listaItensdaVenda = new ArrayList<>();
    }

    public Venda recuperar(String  id){
        return this.repositorioVenda.recuperar(id);
    }

    public void remover(Venda venda){
        this.repositorioVenda.remover(venda);
    }

    public void atualizar(String id, Venda venda){
        this.repositorioVenda.atualizar(id, venda);
    }

    public ArrayList<Venda> recuperarTodos(){
        return this.repositorioVenda.recupertarTudo();
    }

}
