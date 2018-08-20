package negocio;

import execoes.*;
import negocio.entidade.Funcionario;
import negocio.entidade.Gerente;
import negocio.entidade.Usuario;
import repositorio.IRepositorio;
import repositorio.RepositorioUsuario;

import java.util.ArrayList;

public class NegocioUsuario {

    private IRepositorio repositorioUsuario;
    private static NegocioUsuario mySelf;



    private NegocioUsuario(){
        this.repositorioUsuario = new RepositorioUsuario();
    }
    public static NegocioUsuario getInstace(){
        if(mySelf == null){
            mySelf = new NegocioUsuario();
        }
        return mySelf;
    }


    public void cadastrar(Usuario usuario) throws UsuarioJaExisteException, UsuarioInvalidoException, CPFInvalidoException, CPFTamanhoException {

        if(usuario.getCpf().equals("") || usuario.getNome().equals("")){
            throw new UsuarioInvalidoException();
        }else if(recuperar(usuario.getCpf()) != null) {
            throw new UsuarioJaExisteException();
        }else{
            this.repositorioUsuario.cadastrar(usuario);
        }
    }

    private boolean verificarCpfIsDigit(String cpf){
        char[] cpfChar = cpf.toCharArray();
        for (int i = 0; i < cpfChar.length; i++){
            if (!Character.isDigit(cpfChar[i])){
                return false;
            }
        }
        return true;
    }

    public Usuario recuperar(String cpf) throws CPFInvalidoException, CPFTamanhoException {
        if (cpf.length() != 11) {
            throw new CPFTamanhoException(cpf.length());
        }else if (!verificarCpfIsDigit(cpf)){
            throw new CPFInvalidoException();
        }else if(this.repositorioUsuario.recuperar(cpf) == null){
           return null;
        }else{
            return (Usuario) this.repositorioUsuario.recuperar(cpf);
        }
    }

    public void remover(Usuario usuario) throws UsuarioInvalidoException{
        if (usuario == null){
            throw new UsuarioInvalidoException();
        }else{
            this.repositorioUsuario.remover(usuario);
        }

    }

    /**
     * Metodo para atualiza usuario;
     * Obs. Atualizar é diferente de promover;
     * @param cpf
     * @param usuarioNew
     */
    public void atualizar(String cpf, Usuario usuarioNew){
        this.repositorioUsuario.atualizar(cpf, usuarioNew);
    }

    public ArrayList<Usuario> recuperarTodos(){
        return this.repositorioUsuario.recupertarTudo();
    }


    /**
     * O gerente pode dar desconto em compras acima de 300 reis;
     * @param cpfGerente
     * @param senha
     * @param valorVenda
     * @param desconto
     * @return
     * @throws DescontoInvalidoException
     * @throws UsuarioInvalidoException
     */
    public double darDesconto(String cpfGerente, String senha, double valorVenda, double desconto) throws DescontoInvalidoException, UsuarioInvalidoException {
        Gerente gerente = (Gerente) repositorioUsuario.recuperar(cpfGerente);
        if(gerente.getSenha().equals(senha)) {
            if (valorVenda > 300 && desconto > 0) {
                return gerente.darDesconto(desconto);
            } else {
                throw new DescontoInvalidoException(desconto);
            }
        }else{
           throw new UsuarioInvalidoException();
        }
    }
}
