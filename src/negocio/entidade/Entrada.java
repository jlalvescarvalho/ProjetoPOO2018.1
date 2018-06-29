package negocio.entidade;

import java.util.ArrayList;

public class Entrada {

    private int Id;
    private ArrayList<ItemEntrada> itensEntrada;

    public Entrada(ArrayList<ItemEntrada> itensEntrada) {
        this.itensEntrada = itensEntrada;
    }

    public int getId() {
        return Id;
    }

    public ArrayList<ItemEntrada> getItensEntrada() {
        return itensEntrada;
    }

    public void setItensEntrada(ArrayList<ItemEntrada> itensEntrada) {
        this.itensEntrada = itensEntrada;
    }
}
