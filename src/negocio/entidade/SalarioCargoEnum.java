package negocio.entidade;

public enum SalarioCargoEnum {

    Gerente(2500.00), Funcionario(1600.00);

    private final double salario;

    SalarioCargoEnum(double salario) {
        this.salario = salario;
    }

    public double getSalario(){
        return this.salario;
    }
}
