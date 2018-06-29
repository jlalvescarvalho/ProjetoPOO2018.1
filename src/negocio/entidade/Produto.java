package negocio.entidade;

public class Produto {

    private int id;
    private String descricao;
    private double preco;
    private String marca;

    public Produto(int id, String descricao, double preco, String marca) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
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
}
