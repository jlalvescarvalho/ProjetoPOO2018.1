package negocio.entidade;


import execoes.CPFApenasNumerosException;
import execoes.CPFTamanhoException;
import execoes.NomeInvalidoException;

public class Funcionario extends Usuario {

    public Funcionario(String nome, String cpf, Endereco endereco, double salario, String senha) throws CPFTamanhoException, NomeInvalidoException, CPFApenasNumerosException {
        super(nome, cpf, endereco, salario, senha);
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

    @Override
    public void verificarCpf() throws CPFApenasNumerosException, CPFTamanhoException {
        super.verificarCpf();
    }

    @Override
    public void verificarNome() throws NomeInvalidoException {
        super.verificarNome();
    }
}