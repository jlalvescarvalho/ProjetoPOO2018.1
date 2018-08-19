package negocio.entidade;


import execoes.CPFApenasNumerosException;
import execoes.CPFTamanhoException;
import execoes.NomeInvalidoException;
import execoes.SenhaInvalidaException;

public class Funcionario extends Usuario {

    public Funcionario(String nome, String cpf, Endereco endereco, double salario, String senha) throws CPFTamanhoException, NomeInvalidoException, CPFApenasNumerosException, SenhaInvalidaException {
        super(nome, cpf, endereco, salario, senha);
        super.verificarCpf();
        super.verificarNome();
        super.verificarSenha();
    }

    @Override
    public boolean verificarSenha(String senha) {
        if (this.getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }


    public double getBonificacao(){
        return this.getSalario()*0.10;
    }


}