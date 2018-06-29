package negocio.entidade;

public class ItemVenda {

    private Produto produto;
    private int quantidade;
    private double totalItem;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.totalItem = quantidade*produto.getPreco();
    }


}
