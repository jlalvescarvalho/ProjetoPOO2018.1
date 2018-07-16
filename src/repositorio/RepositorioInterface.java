package repositorio;

import java.util.ArrayList;

public interface RepositorioInterface <T>{

    public void cadastrar(T t);
    public T recuperar(String x);
    public void remover(T t);
    public void atualizar(String x, T t);
    public ArrayList<T> recuoertarTudo();
}
