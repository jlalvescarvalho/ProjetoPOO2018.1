package negocio.entidade;

import execoes.UsuarioInvalidoException;
import negocio.NegocioUsuario;

public class Login {

    private Usuario usuario;
    private static Login mySelf;

    private Login() {}

    public static Login getInstance(){
        if (mySelf == null){
            mySelf = new Login();
        }
        return mySelf;
    }

    public int verificarLogin(String cpf, String senha) throws UsuarioInvalidoException {
        final int funcionario = 0;
        final int gerente = 1;

        Usuario usuario = NegocioUsuario.getInstace().recuperar(cpf);
       if(usuario instanceof Gerente && usuario.verificarSenha(senha)) {
            this.usuario = usuario;
            return gerente;
        }else if (usuario instanceof Funcionario && usuario.verificarSenha(senha)){
           this.usuario = usuario;
           return funcionario;
        }else{
            throw new UsuarioInvalidoException();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void logoff(){
        this.usuario = null;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
