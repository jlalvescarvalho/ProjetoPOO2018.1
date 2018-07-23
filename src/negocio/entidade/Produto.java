package negocio.entidade;

/**
 * @author Luciano/Giudicelli
 * Classe representa um produto;
 */
public class Produto {

    private String codigo;
    private String descricao;
    private double preco;
    private String marca;

    public Produto(String codigo, String descricao, double preco, String marca) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return this.getCodigo()+" "+this.getDescricao()+" "+this.preco;
    }

    /**
     * Neste metodo 'p' referencia um produto;
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Produto p = (Produto) obj;
        if(this.codigo.equals(p.getCodigo())){
            return true;
        }
        return false;
    }
}
