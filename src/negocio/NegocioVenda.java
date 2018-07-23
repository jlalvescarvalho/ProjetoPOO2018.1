package negocio;

import negocio.entidade.ItemEstoque;
import negocio.entidade.ItemVenda;
import negocio.entidade.Produto;
import negocio.entidade.Venda;
import repositorio.RepositorioVenda;

import java.util.ArrayList;

public class NegocioVenda {

    private RepositorioVenda repositorioVenda;
    private NegocioProduto negocioProduto;
    private NegocioEstoque negocioEstoque;
    public static NegocioVenda mySelf;
    private ArrayList<ItemVenda> listaItensdaVenda;

    public NegocioVenda(){
        this.listaItensdaVenda = new ArrayList<>();
        this.repositorioVenda = new RepositorioVenda();
    }

    public static NegocioVenda getInstance(){
        if (mySelf == null){
            mySelf = new NegocioVenda();
        }
        return mySelf;
    }

    public void adicionarItem(String codigoProduto, int quantidade){
        Produto produto = recuperarProduto(codigoProduto);
        if(produto != null && verificarDisponibilidade(produto, quantidade)){
            ItemVenda itemVenda = new ItemVenda(produto,quantidade);
            listaItensdaVenda.add(itemVenda);
        }
    }

    public Produto recuperarProduto(String codigo){
        return negocioProduto.getInstance().recuperar(codigo);
    }

    public boolean verificarDisponibilidade(Produto produto, int quantidade){
        ItemEstoque item = negocioEstoque.getInstance().recuperarItemEstoque(produto.getCodigo());
        if(item != null){
            if (item.getQuantidade() >= quantidade){
                return true;
            }
        }
        return false;
    }



    public void cadastrarVenda(Venda venda){

        if(venda != null){
            this.repositorioVenda.cadastrar(venda);
        }
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
