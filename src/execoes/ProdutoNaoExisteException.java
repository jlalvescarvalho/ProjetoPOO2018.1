package execoes;

public class ProdutoNaoExisteException extends Exception {

    public ProdutoNaoExisteException() {
        super("Produto não existe!");
    }
}
