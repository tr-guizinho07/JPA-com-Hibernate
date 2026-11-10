package com.example;

import java.util.Scanner;

import com.example.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Principal {
    public static void main(String[] args) {
        String separador = "\n--------------------------------------------------\n";
        Scanner leitor = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("plataforma-jogos");
        EntityManager em = emf.createEntityManager();
        Usuario usuario = new Usuario();
        boolean continuar = true;
        while (continuar) {
            System.out.println("[1] Consultar usuário");
            System.out.println("[2] Alterar usuário");
            System.out.println("[3] Excluir usuário");
            System.out.println("[4] Sair");
            int opcao = 0;
            try {
                System.out.print("Escolha uma opção: ");
                opcao = leitor.nextInt();
                leitor.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Erro ao ler a opção: " + e.getMessage());
                leitor.nextLine();
                System.out.println(separador);
                continue;
            }
            System.out.println(separador);
            switch (opcao) {
                case 1:
                    int id = 0;
                    try {
                        System.out.print("Digite o ID do usuário: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    usuario = em.find(Usuario.class, id);
                    if (usuario != null) {
                        System.out.println("Usuário encontrado: " + usuario.getNome());
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    System.out.println(separador);
                    break;
                case 2:
                    try {
                        System.out.print("Digite o ID do usuário: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    usuario = em.find(Usuario.class, id);
                    if (usuario != null) {
                        System.out.print("Digite o novo nome do usuário: ");
                        String novoNome = leitor.nextLine();
                        em.getTransaction().begin();
                        usuario.setNome(novoNome);
                        em.getTransaction().commit();
                        System.out.println("Usuário atualizado com sucesso.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    System.out.println(separador);
                    break;
                case 3:
                    try {
                        System.out.print("Digite o ID do usuário: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    usuario = em.find(Usuario.class, id);
                    if (usuario != null) {
                        em.getTransaction().begin();
                        em.remove(usuario);
                        em.getTransaction().commit();
                        System.out.println("Usuário excluído com sucesso.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    System.out.println(separador);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    System.out.println(separador);
                    break;
            }
        }
        em.close();
        emf.close();
    }
}
