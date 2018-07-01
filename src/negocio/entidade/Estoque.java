package negocio.entidade;

import java.util.ArrayList;

public class Estoque {

    private int id;
    private long Codigo;
    private String descricao;
    private int Quant;
    private Produto produto;

    public Estoque(long codigo, String descricao, int quant, Produto produto) {
        Codigo = codigo;
        this.descricao = descricao;
        Quant = quant;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public long getCodigo() {
        return Codigo;
    }

    public void setCodigo(long codigo) {
        Codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuant() {
        return Quant;
    }

    public void setQuant(int quant) {
        Quant = quant;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}