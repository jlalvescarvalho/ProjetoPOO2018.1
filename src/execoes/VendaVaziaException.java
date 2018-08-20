package execoes;

public class VendaVaziaException extends Exception{
    public VendaVaziaException() {
        super("A venda esta vazia. Impossivel salvar.");
    }
}
