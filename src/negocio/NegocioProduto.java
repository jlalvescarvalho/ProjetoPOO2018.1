package negocio;

import negocio.entidade.Produto;
import repositorio.RepositorioProduto;

import java.util.ArrayList;

public class NegocioProduto {

    private RepositorioProduto repositorioProduto;
    public static NegocioProduto mySelf;

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

    public Produto recuperar(long id){
        return repositorioProduto.recuperar(id);
    }

    public ArrayList<Produto> recuperarTodos(){
        return repositorioProduto.recuoertarTudo();
    }

    public void remover(Produto produto){
        repositorioProduto.remover(produto);
    }

    public void atualizar(long id, Produto produto){
        repositorioProduto.atualizar(id, produto);
    }
}
