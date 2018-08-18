package negocio;

import execoes.CodigoInvalidoException;
import execoes.ItemDeEstoqueInvalidoException;
import execoes.ProdutoNaoExisteException;
import execoes.QuantidadeInvalidaException;
import negocio.entidade.ItemEstoque;
import negocio.entidade.Produto;
import repositorio.RepositorioEstoque;

import java.util.ArrayList;

public class NegocioEstoque {

    private RepositorioEstoque repositorioEstoque;
    private static NegocioEstoque mySelf;

    private NegocioEstoque() {
        this.repositorioEstoque = new RepositorioEstoque();
    }

    public static NegocioEstoque getInstace(){
        if(mySelf == null){
            mySelf = new NegocioEstoque();
        }
        return mySelf;
    }

    public void cadastrarEstoque(ItemEstoque itemEstoque){
        if (itemEstoque != null && itemEstoque.getProduto() != null && itemEstoque.getQuantidade() >= 0){
            repositorioEstoque.cadastrar(itemEstoque);
        }
    }

    protected void realizarSaidaEstoque(Produto produto, int quantidadeVendida){
        if(produto != null && quantidadeVendida > 0){
            repositorioEstoque.realizarSaidaEstoque(produto,quantidadeVendida);
        }
    }


    public void realizarEntradaEstoque(String codigo, int quantidade) throws ProdutoNaoExisteException, CodigoInvalidoException, QuantidadeInvalidaException {

        Produto produto;
        if (codigo.equals("") || codigo.equals(" ")){
            throw new CodigoInvalidoException();
        }else{
            produto = NegocioProduto.getInstance().recuperar(codigo);
        }
        if (produto == null){
            throw new ProdutoNaoExisteException();
        }else if(quantidade <= 0){
            throw new QuantidadeInvalidaException();
        }else{
            ItemEstoque itemEstoque = new ItemEstoque(quantidade, produto);
            repositorioEstoque.realizarEntradaEstoque(itemEstoque);
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

    public ArrayList<ItemEstoque> recuperarTudo(){
        return repositorioEstoque.recupertarTudo();
    }

}
