package repositorio;

import java.util.ArrayList;

public interface RepositorioInterface <T>{

    public int recuperarIndice(String t);
    public void cadastrar(T t);
    public T recuperar(String t);
    public void remover(T t);
    public void atualizar(String x, T t);
    public ArrayList<T> recuoertarTudo();
}
