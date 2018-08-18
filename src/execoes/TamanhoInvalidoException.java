package execoes;

public class TamanhoInvalidoException extends Exception{

    public TamanhoInvalidoException(String campo, int tamanho) {
        super("O campo '"+campo+"' deve conter mais de "+tamanho+" caracteres !");
    }
}
