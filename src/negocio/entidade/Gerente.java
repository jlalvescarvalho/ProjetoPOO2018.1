package negocio.entidade;

import negocio.NegocioUsuario;

public class Gerente extends Funcionario {

    private int numeroDeFuncionariosGerenciados;

    public Gerente(String nome, String cpf, Endereco endereco, double salario, String senha, int numeroDeFuncionarioGerenciados) {
        super(nome, cpf, endereco, salario, senha);
        this.numeroDeFuncionariosGerenciados = numeroDeFuncionarioGerenciados;
    }


    public int getNumeroDeFuncionariosGerenciados() {
        return numeroDeFuncionariosGerenciados;
    }

    public void setNumeroDeFuncionariosGerenciados(int numeroDeFuncionariosGerenciados) {
        this.numeroDeFuncionariosGerenciados = numeroDeFuncionariosGerenciados;
    }

    @Override
    public double getBonificacao() {
        return this.getSalario()*0.10+100;
    }
}
