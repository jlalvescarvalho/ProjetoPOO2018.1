package execoes;

public class ItemDeEstoqueInvalidoException extends Exception{

    public ItemDeEstoqueInvalidoException() {
        super("Quantidade ou Codigo invalido !");
    }
}
