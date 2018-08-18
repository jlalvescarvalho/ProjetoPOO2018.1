package negocio.entidade;

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

    @Override
    public String toString() {
        return "Produto: "+this.produto.getDescricao()+" -codigo: "+this.getProduto().getCodigo()+" -Quant.: "+this.quantidade;
    }
}
