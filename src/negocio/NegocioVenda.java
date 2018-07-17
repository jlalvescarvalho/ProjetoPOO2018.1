package negocio;

import negocio.entidade.Venda;
import repositorio.RepositorioVenda;

import java.util.ArrayList;

public class NegocioVenda {

    private RepositorioVenda repositorioVenda;
    public static NegocioVenda mySelf;

    public NegocioVenda(){
        this.repositorioVenda = new RepositorioVenda();
    }

    public static NegocioVenda getInstance(){
        if (mySelf == null){
            mySelf = new NegocioVenda();
        }
        return mySelf;
    }

    public void cadastrar(Venda venda){
        if(venda != null){
            this.repositorioVenda.cadastrar(venda);
        }
    }

    public Venda recuperar(long id){
        return this.repositorioVenda.recuperar(id);
    }

    public void remover(Venda venda){
        this.repositorioVenda.remover(venda);
    }

    public void atualizar(long id, Venda venda){
        this.repositorioVenda.atualizar(id, venda);
    }

    public ArrayList<Venda> recuperarTodos(){
        return this.repositorioVenda.recupertarTudo();
    }

}
