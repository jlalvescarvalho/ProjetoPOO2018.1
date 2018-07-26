package negocio.entidade;


public class Funcionario extends Usuario {

    public Funcionario(String nome, String cpf, Endereco endereco, double salario, String senha) {
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




}