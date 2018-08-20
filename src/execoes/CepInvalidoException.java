package execoes;

public class CepInvalidoException extends Exception{

    public CepInvalidoException() {
        super("O cep de ser no formato 12345678, sem ifem !");
    }
}
