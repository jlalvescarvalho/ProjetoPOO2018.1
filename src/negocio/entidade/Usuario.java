package negocio.entidade;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa um usuario que pode ser funcionario ou gerente.
 */
public abstract class Usuario {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private double salario;
    private String senha;

    public Usuario(String nome, String cpf, Endereco endereco, double salario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.salario = salario;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha(){
        return this.senha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome+" Cpf: "+this.cpf;
    }

    @Override
    public boolean equals(Object obj) {
        Usuario usuario = (Usuario)obj;
        if(this.cpf.equals(usuario.getCpf())){
            return true;
        }
        return true;
    }

    public abstract boolean verificarSenha(String senha);
}
