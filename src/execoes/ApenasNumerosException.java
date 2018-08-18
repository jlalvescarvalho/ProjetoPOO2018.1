package execoes;

public class ApenasNumerosException extends Exception {


    public ApenasNumerosException(String campo) {
        super("O campo " +campo + " deve conter apenas numeros");
    }
}