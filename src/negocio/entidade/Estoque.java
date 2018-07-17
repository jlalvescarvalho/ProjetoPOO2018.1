package negocio.entidade;

import java.util.ArrayList;

public class Estoque {

    private String id;
    private ArrayList<ItemEstoque> itemEstoque;

    public Estoque(String id, ArrayList<ItemEstoque> itemEstoque) {
        this.id = id;
        this.itemEstoque = itemEstoque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ItemEstoque> getItemEstoque() { return itemEstoque; }

    public void setItemEstoque(ArrayList<ItemEstoque> itemEstoque) {
        this.itemEstoque = itemEstoque;
    }
}
