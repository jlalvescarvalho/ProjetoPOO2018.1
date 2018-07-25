package execoes;

public class CPFApenasNumerosException extends Exception{


    public CPFApenasNumerosException() {
        super("Cpf deve ter apenas números");
    }
}
