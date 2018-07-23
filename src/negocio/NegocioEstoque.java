package negocio;

import negocio.entidade.ItemEstoque;
import negocio.entidade.Produto;
import repositorio.IRepositorio;
import repositorio.RepositorioEstoque;

import java.util.ArrayList;

public class NegocioEstoque {

    private RepositorioEstoque repositorioEstoque;
    private static NegocioEstoque mySelf;

    public NegocioEstoque() {
        this.repositorioEstoque = new RepositorioEstoque();
    }

    public static NegocioEstoque getInstance(){
        if (mySelf == null){
            mySelf = new NegocioEstoque();
        }
        return mySelf;
    }

    public void cadastrarEstoque(ItemEstoque itemEstoque){
        if (itemEstoque != null && itemEstoque.getProduto() != null && itemEstoque.getQuantidade() >= 0){
            repositorioEstoque.cadastrar(itemEstoque);
        }
    }

    public void realizarSaidaEstoque(Produto produto, int quantidadeVendida){
        if(produto != null && quantidadeVendida > 0){
            repositorioEstoque.realizarSaidaEstoque(produto,quantidadeVendida);
        }
    }


    public void realizarEntradaEstoque(Produto produto, int quantidade){
        if(produto != null && quantidade > 0){
            repositorioEstoque.realizarEntradaEstoque(produto,quantidade);
        }
    }

    public ItemEstoque recuperarItemEstoque(String codigo){
        if(!codigo.equals(" ")) return (ItemEstoque) repositorioEstoque.recuperar(codigo);
        return null;
    }

    public void removerItemEstoque(String codigo){
        if(!codigo.equals(" ") && codigo.length() > 3){
            ItemEstoque itemEstoque = recuperarItemEstoque(codigo);
            repositorioEstoque.remover(itemEstoque);
        }

    }

    public void atualizarEstoque(String codigoProduto, ItemEstoque itemEstoque){
        if(itemEstoque != null && !codigoProduto.equals(" ")) repositorioEstoque.atualizar(codigoProduto, itemEstoque);
    }

    public ArrayList<ItemEstoque> recuperarTudo(){
        return repositorioEstoque.recupertarTudo();
    }

}
