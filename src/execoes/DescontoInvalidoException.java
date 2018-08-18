package execoes;

public class DescontoInvalidoException extends Exception{

    int valorDesconto;

    public DescontoInvalidoException(int valor) {
        super("Desconto invalido! Você pode dar até 20% de desconto e você deu: "+valor);
        this.valorDesconto = valor;
    }

    public int getValorDesconto() {
        return valorDesconto;
    }
}
