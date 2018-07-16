package repositorio;

import negocio.entidade.Estoque;

import java.util.ArrayList;

public class ReposotorioEstoque implements RepositorioInterface<Estoque> {

private ArrayList<Estoque> listaEstoque;

public ReposotorioEstoque(){
    this.listaEstoque = new ArrayList<>();

}

    @Override
    public int recuperarIndice(String id) {
        for(int i = 0; i < this.listaEstoque.size(); i++){
            if (this.listaEstoque.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void cadastrar(Estoque estoque) {
        this.listaEstoque.add(estoque);
    }

    @Override
    public Estoque recuperar(String x) {
        for (Estoque estoque: this.listaEstoque) {
            if (estoque.getId().equals(x)) {
                return estoque;
            }
        }
        return null;
    }

    @Override
    public void remover(Estoque estoque) {
        if(this.listaEstoque.contains(estoque)){
            this.listaEstoque.remove(estoque);
        }
    }

    @Override
    public void atualizar(String id, Estoque estoque) {
        int indice = recuperarIndice(id);
        this.listaEstoque.set(indice, estoque);
    }

    @Override
    public ArrayList<Estoque> recuoertarTudo() {
        return this.listaEstoque;
    }
}
