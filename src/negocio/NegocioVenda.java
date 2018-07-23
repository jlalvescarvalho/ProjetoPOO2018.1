package negocio;

import negocio.entidade.*;
import repositorio.RepositorioVenda;

import java.util.ArrayList;
import java.util.Date;

public class NegocioVenda {

    private RepositorioVenda repositorioVenda;
    public static NegocioVenda mySelf;
    private ArrayList<ItemVenda> listaItensdaVenda;

    public NegocioVenda(){
        this.listaItensdaVenda = new ArrayList<>();
        this.repositorioVenda = new RepositorioVenda();
    }

    public static NegocioVenda getInstance(){
        if (mySelf == null){
            mySelf = new NegocioVenda();
        }
        return mySelf;
    }

    public void adicionarItem(String codigoProduto, int quantidade){
        try {
            Produto produto = recuperarProduto(codigoProduto);
            if (produto != null && verificarDisponibilidade(produto, quantidade)) {
                ItemVenda itemVenda = new ItemVenda(produto, quantidade);
                listaItensdaVenda.add(itemVenda);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private Produto recuperarProduto(String codigo){
        return NegocioProduto.getInstance().recuperar(codigo);
    }

    private boolean verificarDisponibilidade(Produto produto, int quantidade){
        ItemEstoque item = NegocioEstoque.getInstance().recuperarItemEstoque(produto.getCodigo());
        if(item != null){
            if (item.getQuantidade() >= quantidade){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Venda> gerarRelatorioVendas(Date data1, Date data2){
        ArrayList<Venda> todasAsVendas = repositorioVenda.recupertarTudo();
        ArrayList<Venda> vendasSelecionadas = new ArrayList<>();

        for(int i = 0; i < todasAsVendas.size(); i++){
            if (todasAsVendas.get(i).getData().compareTo(data1) >= 0 && todasAsVendas.get(i).getData().compareTo(data2) <= 0){
                vendasSelecionadas.add(todasAsVendas.get(i));
            }
        }
        return vendasSelecionadas;
    }



    public void cadastrarVenda(Funcionario funcionario, Cliente cliente){
        Venda venda = new Venda(this.listaItensdaVenda, funcionario, cliente);
        this.repositorioVenda.cadastrar(venda);
        for(ItemVenda iv: listaItensdaVenda){
            NegocioEstoque.getInstance().realizarSaidaEstoque(iv.getProduto(), iv.getQuantidade());
        }

    }

    public Venda recuperar(String  id){
        return this.repositorioVenda.recuperar(id);
    }

    public void remover(Venda venda){
        this.repositorioVenda.remover(venda);
    }

    public void atualizar(String id, Venda venda){
        this.repositorioVenda.atualizar(id, venda);
    }

    public ArrayList<Venda> recuperarTodos(){
        return this.repositorioVenda.recupertarTudo();
    }

}
