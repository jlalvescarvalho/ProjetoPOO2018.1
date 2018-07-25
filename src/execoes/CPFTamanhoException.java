package execoes;

public class CPFTamanhoException extends Exception{

    private int tamanho;

    public CPFTamanhoException(int tamanho) {
        super("Tamanho Execido! o CPF deve ter 11 digitos em vez de "+tamanho);

    }
}
