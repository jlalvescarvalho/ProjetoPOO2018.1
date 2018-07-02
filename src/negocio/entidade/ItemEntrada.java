package negocio.entidade;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa cada item de entrada, cada produto e sua quantidade.
 * Servirá também para ter o controle de o que está entrando e saindo
 * do estoque e ter o controle disso.
 */

public class ItemEntrada {

    private int id;
    private String descricao;
    private int quantidade;
    private Produto produto;
    private int cont;

    public ItemEntrada(int quantidade, Produto produto) {
        this.id = cont++;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
