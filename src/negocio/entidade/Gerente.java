package negocio.entidade;

public class Gerente extends Usuario {

    public Gerente(String nome, String cpf, Endereco endereco, String cargo, String senha) {
        super(nome, cpf, endereco, cargo, senha);
    }

    public void gerarRelatoioDeVendas(){

    }

}
