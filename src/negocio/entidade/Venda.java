package negocio.entidade;

import java.util.ArrayList;

public class Venda {

    private long id;
    private ArrayList<ItemVenda> listaVenda;
    private Funcionario funcionario;
    private Cliente cliente;

    public Venda(ArrayList<ItemVenda> listaVenda, Funcionario funcionario, Cliente cliente) {
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
}
