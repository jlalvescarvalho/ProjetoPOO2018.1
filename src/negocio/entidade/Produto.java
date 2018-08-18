package negocio.entidade;

import execoes.ApenasNumerosException;
import execoes.CodigoInvalidoException;
import execoes.DescricaoInvalidaException;
import execoes.TamanhoInvalidoException;

/**
 * @author Luciano/Giudicelli
 * Classe representa um produto;
 */
public class Produto {

    private String codigo;
    private String descricao;
    private double preco;
    private String marca;

    public Produto(String codigo, String descricao, double preco, String marca) throws ApenasNumerosException, TamanhoInvalidoException, DescricaoInvalidaException, CodigoInvalidoException {
        verificarCodigo(codigo);
        verificarDescricao(descricao);
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
    }

    private void verificarCodigo(String codigo) throws ApenasNumerosException, CodigoInvalidoException {
        char[] codigoChar = codigo.toCharArray();
        for(int i = 0; i < codigoChar.length; i++){
            if (!Character.isDigit(codigoChar[i])){
                throw new ApenasNumerosException("codigo");
            }else if(Integer.parseInt((String.valueOf(codigoChar[i]))) < 0){
                throw new CodigoInvalidoException();
            }else if (codigo.length() > 13){
                throw new CodigoInvalidoException();
            }
        }
    }
    private void verificarDescricao(String descricao) throws TamanhoInvalidoException, DescricaoInvalidaException {
        if (descricao.length() < 3){
            throw new TamanhoInvalidoException("descricao", 3);
        }else {
            char[] descricaoChar = descricao.toCharArray();
            boolean gatilho = false;
            for (int i = 0; i < descricaoChar.length; i++) {
                if (descricaoChar[i] == ' ') {
                    gatilho = true;
                }
            }
            if (gatilho) throw new DescricaoInvalidaException();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws TamanhoInvalidoException, DescricaoInvalidaException {
        verificarDescricao(descricao);
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
        return "Codigo: "+this.getCodigo()+" -Descricao: "+this.getDescricao()+" -Preco: "+this.preco;
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
