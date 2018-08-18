package negocio;

import execoes.CodigoInvalidoException;
import execoes.ProdutoNaoExisteException;
import negocio.entidade.ItemEstoque;
import negocio.entidade.Produto;
import repositorio.IRepositorio;
import repositorio.RepositorioProduto;

import java.util.ArrayList;

public class NegocioProduto {

    private IRepositorio repositorioProduto;
    private static NegocioProduto mySelf;

    private NegocioProduto(){
        this.repositorioProduto = new RepositorioProduto();
    }

    public static NegocioProduto getInstance(){
        if (mySelf == null){
            mySelf = new NegocioProduto();
        }
        return mySelf;
    }

    public void cadastrar(Produto produto) throws CodigoInvalidoException {

        if (produto != null) {
                repositorioProduto.cadastrar(produto);
                ItemEstoque it = new ItemEstoque(0,produto);
                NegocioEstoque.getInstace().cadastrarEstoque(it);

        } else {
            throw new CodigoInvalidoException();
        }
    }

    public Produto recuperar(String codigo) throws CodigoInvalidoException, ProdutoNaoExisteException {
        Produto produto;
        if (codigo.equals("") || codigo.equals(" ")){
            throw new CodigoInvalidoException();
        }else {
            produto = (Produto) repositorioProduto.recuperar(codigo);
        }
        if (produto == null){
            throw new ProdutoNaoExisteException();
        }else{
            return produto;
        }
    }

    public ArrayList<Produto> recuperarTodos(){
        return repositorioProduto.recupertarTudo();
    }

    public void remover(String codigo) throws CodigoInvalidoException, ProdutoNaoExisteException {
        Produto produto = recuperar(codigo);

        if (produto != null){
            repositorioProduto.remover(produto);
        }else{
            throw new CodigoInvalidoException();
        }

    }

    public void atualizar(String codigo, Produto produto){

        repositorioProduto.atualizar(codigo, produto);
    }
}
