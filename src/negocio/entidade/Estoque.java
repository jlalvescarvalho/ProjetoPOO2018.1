package negocio.entidade;

import java.util.ArrayList;

public class Estoque {

    private String nome;
    private ArrayList<ItemEstoque> itemEstoque;

    public Estoque(String nome, ArrayList<ItemEstoque> itemEstoque) {
        this.nome = nome;
        this.itemEstoque = itemEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<ItemEstoque> getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ArrayList<ItemEstoque> itemEstoque) {
        this.itemEstoque = itemEstoque;
    }
}
