package negocio.entidade;

import execoes.CampoEnderecoVazioException;
import execoes.CepInvalidoException;

/**
 * @author luciano/Giudiceli
 * Esta classe representa o endereço do cliente ou Usuario
 */

public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;

    public Endereco(String rua, String numero, String bairro, String cep, String cidade) throws CampoEnderecoVazioException, CepInvalidoException {
        verificarEntradas(rua, numero,bairro,cep,cidade);
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) throws CampoEnderecoVazioException, CepInvalidoException {
        verificarEntradas(rua, numero,bairro,cep,cidade);
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws CampoEnderecoVazioException, CepInvalidoException {
        verificarEntradas(rua, numero,bairro,cep,cidade);
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) throws CampoEnderecoVazioException, CepInvalidoException {
        verificarEntradas(rua, numero,bairro,cep,cidade);
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) throws CampoEnderecoVazioException, CepInvalidoException {
        verificarEntradas(rua, numero,bairro,cep,cidade);
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) throws CampoEnderecoVazioException, CepInvalidoException {
        verificarEntradas(rua, numero,bairro,cep,cidade);
        this.cep = cep;
    }

    private void verificarEntradas(String rua, String numero, String bairro, String cep, String cidade) throws CampoEnderecoVazioException, CepInvalidoException, NumberFormatException {
        if (rua.length() == 0){
            throw new CampoEnderecoVazioException("Rua");
        }else if(numero.length() == 0){
            throw new CampoEnderecoVazioException("Numero");
        }else if(bairro.length() == 0){
            throw new CampoEnderecoVazioException("Bairro");
        }else if (cep.length() == 0){
            throw new CampoEnderecoVazioException("Cep");
        }else if(cidade.length() == 0){
            throw new CampoEnderecoVazioException("Cidade");
        }else if (cep.length() != 8){
            throw new CepInvalidoException();
        }
        char[] cepChar = cep.toCharArray();
        for(int i = 0; i < cepChar.length; i++){
            if (!Character.isDigit(cepChar[i])){
                throw new NumberFormatException();
            }
        }

    }
}
