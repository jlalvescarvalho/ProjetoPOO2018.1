package repositorio;

import negocio.entidade.Cliente;

import java.util.ArrayList;

public class RepositorioCliente implements RepositorioInterface<Cliente>{

    private ArrayList<Cliente> listaCliente;

    public RepositorioCliente() {
        this.listaCliente = new ArrayList<>();
    }


   @Override
    public int recuperarIndice(String cpf){
        for(int i = 0; i < this.listaCliente.size(); i++){
            if (this.listaCliente.get(i).getCpf().equals(cpf)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void cadastrar(Cliente cliente) {
        this.listaCliente.add(cliente);
    }

    @Override
    public Cliente recuperar(String cpf) {
        for (Cliente c : this.listaCliente) {
            if (c.getCpf() == cpf) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void remover(Cliente cliente) {
        if(this.listaCliente.contains(cliente)){
            this.listaCliente.remove(cliente);
        }
    }

    @Override
    public void atualizar(String cpf, Cliente cliente) {
        int indice = recuperarIndice(cpf);
        this.listaCliente.set(indice, cliente);
    }

    @Override
    public ArrayList<Cliente> recuoertarTudo() {
        return this.listaCliente;
    }
}
