package execoes;

public class UsuarioNaoExisteException extends Exception{

    public UsuarioNaoExisteException() {
        super("Usuario e/ou senha não existe! ");
    }
}
