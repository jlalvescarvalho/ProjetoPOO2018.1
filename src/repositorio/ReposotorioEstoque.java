package repositorio;

import negocio.entidade.Estoque;

import java.util.ArrayList;

public class ReposotorioEstoque implements RepositorioInterface<Estoque> {

private ArrayList<Estoque> estoque;

public ReposotorioEstoque(){
    this.estoque = new ArrayList<>();

}
    @Override
    public void cadastrar(Estoque estoque) {

    }

    @Override
    public Estoque recuperar(String x) {
        return null;
    }

    @Override
    public void remover(Estoque estoque) {

    }

    @Override
    public void atualizar(String x, Estoque estoque) {

    }

    @Override
    public ArrayList<Estoque> recuoertarTudo() {
        return null;
    }
}
