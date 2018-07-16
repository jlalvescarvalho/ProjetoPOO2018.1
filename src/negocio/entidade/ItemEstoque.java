package negocio.entidade;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa cada item de entrada, cada produto e sua quantidade.
 * Servirá também para ter o controle de o que está entrando e saindo
 * do estoque e ter o controle disso.
 */

public class ItemEstoque {

    private int quantidade;
    private Produto produto;

    public ItemEstoque(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
