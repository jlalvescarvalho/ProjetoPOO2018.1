package repositorio;

public interface RepositorioInterface <T>{

    public void cadastrar(T t);
    public T recuperar(long x);
    public void remover(T t);
    public void atualizar(long x, T t);
}
