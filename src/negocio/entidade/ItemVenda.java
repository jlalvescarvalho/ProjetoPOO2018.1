package negocio.entidade;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa cada item presente da venda
 * cada item é composto por um produto a quantidade desse produto
 * e o valor total desse item;
 */
    public class ItemVenda {

    private Produto produto;
    private int quantidade;
    private double totalItem;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.totalItem = quantidade*produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }
}
