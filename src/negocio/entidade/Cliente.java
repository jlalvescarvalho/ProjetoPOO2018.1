package negocio.entidade;

/**
 * @author luciano/Giudicelli
 * Representação de um cliente.
 */

public class Cliente {

    private String nome;
    private String cpf;
    private char genero;
    private Endereco endereco;

    public Cliente(String nome, String cpf, char genero, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.genero = genero;
        this.endereco = endereco;
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

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente c = (Cliente)obj;

        if(c.getCpf().equals(this.cpf)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cpf: "+this.cpf+" Nome: "+this.nome+" Genero: "+this.genero;
    }
}
