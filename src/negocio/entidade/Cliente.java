package negocio.entidade;

import execoes.CPFApenasNumerosException;
import execoes.CPFTamanhoException;
import execoes.NomeInvalidoException;

/**
 * @author luciano/Giudicelli
 * Representação de um cliente.
 */

public class Cliente {

    private String nome;
    private String cpf;
    private Endereco endereco;

    public Cliente(String nome, String cpf, Endereco endereco) throws NomeInvalidoException, CPFApenasNumerosException, CPFTamanhoException {
        verificarCpf(cpf);
        verificarNome(nome);
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeInvalidoException {
        verificarNome(nome);
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws CPFApenasNumerosException, CPFTamanhoException {
        verificarCpf(cpf);
        this.cpf = cpf;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void verificarCpf(String cpf) throws CPFApenasNumerosException, CPFTamanhoException {
        char[] cpfChar = cpf.toCharArray();
        for(int i = 0; i < cpfChar.length; i++){
            if (!Character.isDigit(cpfChar[i])){
                throw new CPFApenasNumerosException();
            }
        }
        if (cpf.length() != 11){
            throw new CPFTamanhoException(cpf.length());
        }

    }

    public void verificarNome(String nome) throws NomeInvalidoException {
        if(nome.equals(" ") || nome.length() < 3) throw new NomeInvalidoException();
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
        return "Cpf: "+this.cpf+" Nome: "+this.nome;
    }
}
