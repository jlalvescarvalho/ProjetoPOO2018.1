package negocio.entidade;

import java.util.ArrayList;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa a venda
 * tal venda deve ser constituida por uma lista de itens, por um funcionario e um cliente.
 * deve ser possivel também realizar uma venda avulsa(sem declarar cliente cliente).
 */
public class Venda {

    private long id;
    private ArrayList<ItemVenda> listaVenda;
    private Funcionario funcionario;
    private Cliente cliente;
    private int cont;

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario, Cliente cliente) {
        this.id = cont++;
        this.listaVenda = listaVenda;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario) {
        this.listaVenda = listaVenda;
        this.funcionario = funcionario;
    }

    public long getId() {
        return id;
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
