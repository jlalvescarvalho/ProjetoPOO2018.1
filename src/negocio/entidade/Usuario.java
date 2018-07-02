package negocio.entidade;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa um usuario que pode ser funcionario ou gerente.
 */
public class Usuario {

    private String nome;
    private long cpf;
    private Endereco endereco;
    private String cargo;
    private String senha;

    public Usuario(String nome, long cpf, Endereco endereco, String cargo, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
