package negocio.entidade;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Luciano/Giudicelli
 * A classe entrada vai representar um lote de entradade produtos
 * toda vez que o mercado receber mercadorias o funcionario deve dar entrada
 * de tais produtos no estoque.
 */

public class Entrada {

    private int id;
    private int cont;
    private ArrayList<ItemEstoque> itensEntrada;
    private Date data;
    private Funcionario func;

    public Entrada(ArrayList<ItemEstoque> itensEntrada, Date date, Funcionario func) {
        this.id = cont++;
        this.itensEntrada = itensEntrada;
        this.data = date;
        this.func = func;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ItemEstoque> getItensEntrada() {
        return itensEntrada;
    }

    public void setItensEntrada(ArrayList<ItemEstoque> itensEntrada) {
        this.itensEntrada = itensEntrada;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }
}
