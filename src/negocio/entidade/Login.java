package negocio.entidade;

import negocio.NegocioUsuario;


public class Login {


    public boolean verificarLogin(String cpf, String senha){
        Usuario f = NegocioUsuario.getInstance().recuperar(cpf);

        if(cpf.equals(f.getCpf()) && senha.equals(f.getSenha())){
            return true;
        }else{
            return false;
        }
    }
}
