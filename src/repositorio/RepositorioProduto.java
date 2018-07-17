package repositorio;

import negocio.entidade.Produto;

import java.util.ArrayList;

public class RepositorioProduto implements IRepositorio<Produto> {

    private ArrayList<Produto> produtos;

    public RepositorioProduto() {
        this.produtos = new ArrayList<>();

    }

    public int recuperarIndice(String id){
        for(int i = 0; i < this.produtos.size(); i++){
            if (this.produtos.get(i).getCodigo().equals(id) ){
                return i;
            }
        }
        return -1;
    }


    @Override
    public void cadastrar(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public Produto recuperar(String id) {
        for (int i = 0; i < this.produtos.size();i++){
            if (this.produtos.get(i).getCodigo() == id){
                return this.produtos.get(i);
            }
        }
        return null;
    }

    @Override
    public void remover(Produto produto) {
        if (this.produtos.contains(produto)){
            this.produtos.remove(produto);
        }
    }

    @Override
    public void atualizar(String id, Produto produto) {
        int indice = recuperarIndice(id);
        this.produtos.set(indice, produto);
    }

    @Override
    public ArrayList<Produto> recupertarTudo() {
        return this.produtos;
    }
}
