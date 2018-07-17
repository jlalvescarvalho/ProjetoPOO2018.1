package repositorio;

import negocio.entidade.Usuario;

import java.util.ArrayList;

public class RepositorioUsuario implements IRepositorio<Usuario> {

    private ArrayList<Usuario> usuarios;

    public RepositorioUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public int recuperarIndice(String cpf){
        for(int i = 0; i < this.usuarios.size(); i++){
            if (this.usuarios.get(i).getCpf().equals(cpf)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void cadastrar(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public Usuario recuperar(String cpf) {
        for (int i = 0; i < this.usuarios.size();i++){
            if (this.usuarios.get(i).getCpf().equals(cpf)){
                return this.usuarios.get(i);
            }
        }
        return null;
    }

    @Override
    public void remover(Usuario usuario) {
        if (this.usuarios.contains(usuario)){
            this.usuarios.remove(usuario);
        }
    }

    @Override
    public void atualizar(String cpf, Usuario usuario) {
        int indice = recuperarIndice(cpf);
        this.usuarios.set(indice, usuario);
    }

    @Override
    public ArrayList<Usuario> recupertarTudo() {
        return this.usuarios;
    }
}
