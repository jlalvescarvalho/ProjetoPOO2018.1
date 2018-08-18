package execoes;

public class CampoTipoUsuarioVazioException extends Exception{

    public CampoTipoUsuarioVazioException() {
        super("Escolha um tipo de usuario.");
    }
}
