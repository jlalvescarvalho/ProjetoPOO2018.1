package negocio.entidade;


import execoes.*;

public class Gerente extends Funcionario {

    private int numeroDeFuncionariosGerenciados;
    private int descontoMaximo = 20;

    public Gerente(String nome, String cpf, Endereco endereco, double salario, String senha, int numeroDeFuncionarioGerenciados) throws CPFApenasNumerosException, NomeInvalidoException, CPFTamanhoException, SenhaInvalidaException {
        super(nome, cpf, endereco, salario, senha);
        super.verificarCpf();
        super.verificarNome();
        super.verificarSenha();
        this.numeroDeFuncionariosGerenciados = numeroDeFuncionarioGerenciados;
    }

    /**
     * O gerente pode dar desconto em compras acimo de 300 reais
     * @param desconto
     * @return
     * @throws DescontoInvalidoException
     */
    public double darDesconto(double desconto) throws DescontoInvalidoException {
        if(desconto <= this.descontoMaximo && desconto > 0){
           return desconto/100;
        }else{
            throw new DescontoInvalidoException(desconto);
        }
    }

    public int getNumeroDeFuncionariosGerenciados() {
        return numeroDeFuncionariosGerenciados;
    }

    public void setNumeroDeFuncionariosGerenciados(int numeroDeFuncionariosGerenciados) {
        this.numeroDeFuncionariosGerenciados = numeroDeFuncionariosGerenciados;
    }

    @Override
    public boolean verificarSenha(String senha) {
        if (this.getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }


}
