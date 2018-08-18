package repositorio;

import negocio.entidade.ItemEstoque;
import negocio.entidade.ItemVenda;
import negocio.entidade.Produto;

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
    if (!listaEstoque.contains(itemEstoque)) {
        this.listaEstoque.add(itemEstoque);
    }
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
    public void remover(ItemEstoque itemEstoque) throws NullPointerException{
        if(this.listaEstoque.contains(itemEstoque)){
            this.listaEstoque.remove(itemEstoque);
        }
    }

    @Override
    public void atualizar(String codigo, ItemEstoque itemEstoque) {
        int indice = recuperarIndice(codigo);
        this.listaEstoque.set(indice, itemEstoque);
    }

    public void realizarSaidaEstoque(Produto produto, int quantidadeVendida){
        for (ItemEstoque ie: listaEstoque){
            if (ie.getProduto().equals(produto)){
                ie.setQuantidade(ie.getQuantidade()-quantidadeVendida);
                break;
            }
        }
    }

    public void realizarEntradaEstoque(ItemEstoque itemEstoque){
            for (ItemEstoque ie : listaEstoque) {
                if (ie.getProduto().equals(itemEstoque.getProduto())) {
                    ie.setQuantidade(ie.getQuantidade() + itemEstoque.getQuantidade());
                    break;
                }
            }
    }

    @Override
    public ArrayList<ItemEstoque> recupertarTudo() {
        return this.listaEstoque;
    }
}
