package main;

import fachada.Fachada;
import negocio.entidade.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcao= 1;
        while(opcao != 0) {
            System.out.println("Digite sua opcao:");
            System.out.println("1 - Cadastrar Produto!");
            System.out.println("2 - Remover Produto!");
            System.out.println("3 - Recuperar todos os produtos! ");
            System.out.println("4 - Cadastrar Cliente!");
            System.out.println("5 - Remover Cliente!");
            System.out.println("6 - Recupear todos os Cliente!");
            System.out.println("7 - Cadastrar usuario!");
            System.out.println("8 - Remover Usuario!");
            System.out.println("9 - Recuperar todos os Usuarios!");
            System.out.println("10 - Cadastrar Venda!");
            System.out.println("11 - Remover Venda1");
            System.out.println("12 - Recuperar vendas!");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    long id;
                    double preco;
                    String descricao, marca;

                    System.out.println("Cadastrar Produto:");
                    System.out.println("Código:");
                    id = Long.parseLong(sc.next());
                    sc.nextLine();
                    System.out.println("Descricao:");
                    descricao = sc.nextLine();
                    System.out.println("Marca:");
                    marca = sc.nextLine();
                    System.out.println("Preco:");
                    preco = Double.parseDouble(sc.next());


                    Produto p = new Produto(id, descricao,preco, marca);
                    Fachada.getInstance().cadastrarProduto(p);
                    break;

                case 2:
                    System.out.println("Para remover o produto digite o ID do tal:");
                    id = sc.nextInt();
                    Produto p1 = Fachada.getInstance().recuperarProduto(id);
                    Fachada.getInstance().removerProduto(p1);
                    break;

                case 3:
                    ArrayList<Produto> produtos = Fachada.getInstance().recuperarTodosProdutos();

                    for (Produto pr : produtos) {
                        System.out.println("Codigo: "+pr.getCodigo()+" Descrição: "+pr.getDescricao()+" Marca: "+pr.getMarca()+" Preço: "+pr.getPreco());
                    }
                    break;

                case 4:
                    System.out.println("Cadastrar Cliente");
                    System.out.println("Nome:");
                    String nome = sc.next();
                    System.out.println("CPF:");
                    long cpf = sc.nextLong();
                    System.out.println("Genero:");
                    char genero = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println("#Endereço#");
                    System.out.println("Rua:");
                    String rua = sc.nextLine();
                    System.out.println("Numero:");
                    int numero = sc.nextInt();
                    System.out.println("Cidade:");
                    String cidade = sc.next();

                    Fachada.getInstance().cadastrarCliente(nome, cpf, genero, rua, numero, cidade);
                    break;

                case 5:
                    System.out.println("Para remover cliente digite o CPF do tal:");
                    cpf = sc.nextLong();
                    Cliente c = Fachada.getInstance().recuperarCliente(cpf);
                    Fachada.getInstance().removerCliente(c);
                    break;

                case 6:
                    System.out.println("Recuperar todos os clientes:");
                    ArrayList<Cliente> clientes = Fachada.getInstance().recuperarTodosCliente();

                    for (Cliente cliente : clientes) {
                        System.out.println(cliente.toString());
                    }
                    break;

                case 7:
                    sc.nextLine();
                    System.out.println("Cadastrar funcionario!");
                    System.out.println("Nome:");
                    nome = sc.nextLine();
                    System.out.println("CPF");
                    cpf = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Rua:");
                    rua = sc.nextLine();
                    System.out.println("Numero:");
                    numero = sc.nextInt();
                    System.out.println("Cidade");
                    cidade = sc.next();
                    System.out.println("Cargo:");
                    String cargo = sc.next();
                    System.out.println("Senha:");
                    String senha = sc.next();

                    Fachada.getInstance().cadastrarUsuario(nome, cpf, rua, numero, cidade, cargo, senha);
                    break;

                case 8:
                    System.out.println("Remover usuario:");

                    System.out.println("Para remover Usuario digite o cpf do tal:");
                    cpf = sc.nextLong();
                    Fachada.getInstance().removerUsuario(cpf);
                    break;
                case 9:
                    System.out.println("Recuperar todos os Usuarios:");

                    ArrayList<Usuario> usuarios = Fachada.getInstance().recuperarTodosUsuarios();
                    for(Usuario u: usuarios){
                        System.out.println("Nome:"+ u.getNome() + " CPF:" + u.getCargo()+ " Cargo:" + u.getCargo());
                    }
                    break;

                case 10:
                    String novaVenda = "s";
                    ArrayList<ItemVenda> listaItens = new ArrayList<>();

                    while (!novaVenda.equals("n")){
                        System.out.println("Cadastrar Venda:");
                        System.out.println("codigo do produto:");
                        id = sc.nextLong();
                        System.out.println("Quantidade:");
                        int quantidade = sc.nextInt();

                        Produto produto = Fachada.getInstance().recuperarProduto(id);

                        ItemVenda it = new ItemVenda(produto, quantidade);
                        listaItens.add(it);


                        System.out.println("Adicionar mais algum produto. s/n");
                        novaVenda = sc.next();
                    }

                    System.out.println("CPF do Funcionario:");
                    long cpfFunc = sc.nextLong();

                    System.out.println("CPF do Cliente:");
                    long cpfCliente = sc.nextLong();

                    Funcionario func = (Funcionario) Fachada.getInstance().recuperarUsuario(cpfFunc);
                    Cliente cliente = Fachada.getInstance().recuperarCliente(cpfCliente);

                    Venda venda = new Venda(listaItens, func, cliente);
                    Fachada.getInstance().cadastrarVenda(venda);
                    break;
                case 11:
                    System.out.println("Remover venda:");

                    System.out.println("Para remover venda digiti o ID do tal:");
                    id = sc.nextLong();
                    Fachada.getInstance().removerVenda(id);
                    break;
                case 12:
                    ArrayList<Venda> vendas = Fachada.getInstance().recuperarTodasVendas();

                    for(int i = 0; i < vendas.size(); i++){

                        ArrayList<ItemVenda> it = vendas.get(i).getListaVenda();

                        for(int j = 0; j < it.size(); j++){
                            System.out.println("Item "+i+" : "+it.get(j).getProduto().getDescricao()+" Quant: "+it.get(j).getQuantidade());
                        }

                        System.out.println("Cliente: "+vendas.get(i).getCliente().getNome());
                        System.out.println("Funcionario: "+vendas.get(i).getFuncionario().getNome());

                        System.out.println("Total venda:"+vendas.get(i).getTotal());
                    }
                    break;

                    default:
                        System.out.println("Opção invalida!");

            }

        }



    }

}