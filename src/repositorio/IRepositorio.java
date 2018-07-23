package repositorio;

import java.util.ArrayList;

public interface IRepositorio<T>{

    int recuperarIndice(String t);
    void cadastrar(T t);
    T recuperar(String t);
    void remover(T t);
    void atualizar(String x, T t);
    ArrayList<T> recupertarTudo();

}
