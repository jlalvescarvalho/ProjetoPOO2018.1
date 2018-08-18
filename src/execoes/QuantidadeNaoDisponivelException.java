package execoes;

public class QuantidadeNaoDisponivelException extends Exception{

    public QuantidadeNaoDisponivelException(int quantidadeSolicitada, int quantidadeDisponivel) {
        super("Esta quantidade "+quantidadeSolicitada+" não esta disponivel ! Quantidade disponivel : "+quantidadeDisponivel);
    }
}
