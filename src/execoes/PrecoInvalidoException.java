package execoes;

public class PrecoInvalidoException extends Exception{
    public PrecoInvalidoException() {
        super("Preco menor que zero");
    }
}
