package execoes;

public class ClienteJaExiteException extends Exception{

    public ClienteJaExiteException() {
        super("Esse cliente ja exite !");
    }
}
