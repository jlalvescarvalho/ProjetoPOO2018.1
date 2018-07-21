package negocio.entidade;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa a venda
 * tal venda deve ser constituida por uma lista de itens, por um funcionario e um cliente.
 * deve ser possivel também realizar uma venda avulsa(sem declarar cliente cliente).
 */
public class Venda {

    private String id;
    private ArrayList<ItemVenda> listaVenda;
    private Funcionario funcionario;
    private Cliente cliente;
    private Date data;
    private int cont;

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario, Cliente cliente, Date data) {
        this.listaVenda = listaVenda;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.data = data;
    }

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario, Date data) {
        this.listaVenda = listaVenda;
        this.funcionario = funcionario;
        this.data = data;
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

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<ItemVenda> getListaVenda() {
        return listaVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
}
