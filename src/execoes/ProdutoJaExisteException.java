package execoes;

public class ProdutoJaExisteException extends Exception{

    public ProdutoJaExisteException() {
        super("Este produto ja esta cadastrado!");
    }
}
