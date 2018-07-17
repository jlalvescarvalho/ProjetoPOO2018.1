package negocio;

import negocio.entidade.Produto;
import repositorio.RepositorioProduto;

import java.util.ArrayList;

public class NegocioProduto {

    private RepositorioProduto repositorioProduto;
    private static NegocioProduto mySelf;

    public NegocioProduto(){
        this.repositorioProduto = new RepositorioProduto();
    }

    public static NegocioProduto getInstance(){
        if(mySelf == null){
            mySelf = new NegocioProduto();
        }
        return mySelf;
    }

    public void cadastrar(Produto produto){
        if(produto != null){
            repositorioProduto.cadastrar(produto);
        }
    }

    public Produto recuperar(String codigo){
        return repositorioProduto.recuperar(codigo);
    }

    public ArrayList<Produto> recuperarTodos(){
        return repositorioProduto.recupertarTudo();
    }

    public void remover(Produto produto){
        repositorioProduto.remover(produto);
    }

    public void atualizar(String codigo, Produto produto){
        repositorioProduto.atualizar(codigo, produto);
    }
}
