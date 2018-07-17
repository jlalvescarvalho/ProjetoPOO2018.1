package repositorio;

import negocio.entidade.Venda;

import java.util.ArrayList;

public class RepositorioVenda implements IRepositorio<Venda> {

    private ArrayList<Venda> vendas;

    public RepositorioVenda() {
        this.vendas = new ArrayList<>();
    }


    public int recuperarIndice(long id){
        for(int i = 0; i < this.vendas.size(); i++){
            if (this.vendas.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void cadastrar(Venda venda) {
        this.vendas.add(venda);
    }

    @Override
    public Venda recuperar(long id) {
        for (int i = 0; i < this.vendas.size();i++){
            if (this.vendas.get(i).getId() == id){
                return this.vendas.get(i);
            }
        }
        return null;
    }

    @Override
    public void remover(Venda venda) {
        if (this.vendas.contains(venda)){
            this.vendas.remove(venda);
        }
    }

    @Override
    public void atualizar(long id, Venda venda) {
        int indice = recuperarIndice(id);
        this.vendas.set(indice, venda);
    }

    @Override
    public ArrayList<Venda> recupertarTudo() {
        return this.vendas;
    }
}
