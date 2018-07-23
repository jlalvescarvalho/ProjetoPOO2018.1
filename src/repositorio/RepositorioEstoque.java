package repositorio;

import negocio.entidade.Estoque;
import negocio.entidade.ItemEstoque;

import java.util.ArrayList;

public class RepositorioEstoque implements IRepositorio<ItemEstoque> {

private ArrayList<ItemEstoque> listaEstoque;

public RepositorioEstoque(){
    this.listaEstoque = new ArrayList<>();

}

    @Override
    public int recuperarIndice(String codigo) {
        for(int i = 0; i < this.listaEstoque.size(); i++){
            if (this.listaEstoque.get(i).getProduto().getCodigo().equals(codigo)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void cadastrar(ItemEstoque itemEstoque) {
        this.listaEstoque.add(itemEstoque);
    }

    @Override
    public ItemEstoque recuperar(String codigo) {
        for (ItemEstoque ie: this.listaEstoque) {
            if (ie.getProduto().getCodigo().equals(codigo)) {
                return ie;
            }
        }
        return null;
    }

    @Override
    public void remover(ItemEstoque itemEstoque) {
        if(this.listaEstoque.contains(itemEstoque)){
            this.listaEstoque.remove(itemEstoque);
        }
    }

    @Override
    public void atualizar(String codigo, ItemEstoque itemEstoque) {
        int indice = recuperarIndice(codigo);
        this.listaEstoque.set(indice, itemEstoque);
    }

    @Override
    public ArrayList<ItemEstoque> recupertarTudo() {
        return this.listaEstoque;
    }
}
