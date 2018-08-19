package execoes;

public class ItemVendaInvalidoException extends Exception{
    public ItemVendaInvalidoException() {
        super("Item nao encontrado");
    }
}
