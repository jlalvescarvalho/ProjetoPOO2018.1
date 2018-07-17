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

    public void cadastrarEstoque(String nome, ArrayList<ItemEstoque> itens){
        if(itens.size() > 0 && nome.length() > 3) {
            if (recuperarEstoque(nome) == null) {
                Estoque estoque = new Estoque(nome, itens);
                repositorioEstoque.cadastrar(estoque);
            }
        }
    }

    public Estoque recuperarEstoque(String nome){
        if(!nome.equals(" ")) return (Estoque) repositorioEstoque.recuperar(nome);
        return null;
    }

    public void removerEstoque(String nome){
        if(!nome.equals(" ") && nome.length() > 3){
            Estoque estoque = recuperarEstoque(nome);
            repositorioEstoque.remover(estoque);
        }

    }

    public void atualizarEstoque(String nome, Estoque estoque){
        if(estoque != null && nome.length() > 3) repositorioEstoque.atualizar(nome, estoque);
    }

    public ArrayList<Estoque> recuperarTudo(){
        return repositorioEstoque.recupertarTudo();
    }

}
