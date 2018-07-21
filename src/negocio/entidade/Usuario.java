package negocio.entidade;

import negocio.NegocioUsuario;

/**
 * @author Luciano/Giudicelli
 * Esta classe representa um usuario que pode ser funcionario ou gerente.
 */
public abstract class Usuario {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private String senha;

    public Usuario(String nome, String cpf, Endereco endereco, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
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

}
