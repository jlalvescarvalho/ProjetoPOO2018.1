package negocio;

import negocio.entidade.Estoque;
import negocio.entidade.ItemEstoque;
import repositorio.IRepositorio;
import repositorio.RepositorioEstoque;

import java.util.ArrayList;

public class NegocioEstoque {

    private IRepositorio repositorioEstoque;
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
