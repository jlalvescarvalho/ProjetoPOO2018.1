package negocio.entidade;
import java.util.ArrayList;
import java.util.Date;
/**
 * @author Luciano/Giudicelli
 * Esta classe representa a venda
 * tal venda deve ser constituida por uma lista de itens, por um Funcionario e um cliente.
 * deve ser possivel também realizar uma venda avulsa(sem declarar cliente cliente).
 */
public class Venda {

    private String id;
    private ArrayList<ItemVenda> listaVenda;
    private Funcionario funcionario;
    private Cliente cliente;
    private Date data;

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario, Cliente cliente) {
        this.listaVenda = listaVenda;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.data = new Date();
    }

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario) {
        this.listaVenda = listaVenda;
        this.funcionario = funcionario;
        this.data = new Date();
    }

    public String getId() {
        return id;
    }

    public void setListaVenda(ArrayList<ItemVenda> listaVenda) {
        this.listaVenda = listaVenda;
    }

    public Date getData() {
        return data;
    }

    public ArrayList<ItemVenda> getListaVenda() {
        return listaVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal(){
        double tt = 0.0;

        for(ItemVenda it: listaVenda){
            tt += it.getTotalItem();
        }
        return tt;
    }

    @Override
    public String toString() {
        return "Valor da venda: "+this.getTotal()+" Funcionario: "+this.funcionario;
    }
}
