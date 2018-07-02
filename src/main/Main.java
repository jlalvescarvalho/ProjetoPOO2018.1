package main;

import fachada.Fachada;
import negocio.entidade.Cliente;
import negocio.entidade.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

         int opcao= 1;
         while(opcao != 0) {
             System.out.println("Digite sua opcao:");
             opcao = sc.nextInt();
             System.out.println("1; Cadastrar Produto!");
             System.out.println("2; Remover Produto!");
             System.out.println("3; Recuperar todos os produtos! ");
             System.out.println("4; Cadastrar Cliente!");
             System.out.println("5; Remover Cliente!");
             switch (opcao) {
                 case 1:
                     System.out.println("Cadastrar Produto:");
                     System.out.println("ID:");
                     int id = sc.nextInt();
                     System.out.println("Descricao:");
                     String descricao = sc.nextLine();
                     System.out.println("Preco:");
                     double preco = sc.nextDouble();
                     System.out.println("Marca");
                     String marca = sc.next();

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
                     ArrayList<Produto> produtos = Fachada.getInstance().recuperarTudosProdutos();

                     for(int i = 0; i <= produtos.size(); i++) {
                         System.out.println(produtos.get(i).toString());
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
                     System.out.println("Emdereco:");
                     System.out.println("Rua:");
                     String rua = sc.next();
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

             }

         }



        }

}
