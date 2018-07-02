package negocio.entidade;

public class Cliente {

    private String nome;
    private long cpf;
    private char genero;
    private Endereco endereco;

    public Cliente(String nome, long cpf, char genero, Endereco endereco) {
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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
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

        if(c.getCpf() == this.cpf){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cpf: "+this.cpf+" Nome: "+this.nome+" Genero: "+this.genero;
    }
}
