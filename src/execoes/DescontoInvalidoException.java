package execoes;

public class DescontoInvalidoException extends Exception{

    private double valorDesconto;

    public DescontoInvalidoException(double valor) {
        super("Desconto invalido! Você pode dar até 20% de desconto e você deu: "+valor);
        this.valorDesconto = valor;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }
}
