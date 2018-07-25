package execoes;

public class UsuarioJaExisteException extends Exception{

    public UsuarioJaExisteException() {
        super("Este usuario já existe !");
    }
}
