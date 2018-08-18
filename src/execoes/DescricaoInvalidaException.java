package execoes;

public class DescricaoInvalidaException extends Exception{

    public DescricaoInvalidaException() {
        super("Descricao invalida !  A descricao deve conter caracteres.");
    }
}
