package execoes;

public class CampoEnderecoVazioException extends Exception{
    public CampoEnderecoVazioException(String campo) {
        super("Campo "+campo+" vazio !");
    }
}
