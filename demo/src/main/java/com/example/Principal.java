package com.example;

import java.util.Scanner;

import com.example.model.Usuario;
import com.example.model.Jogo;
import com.example.model.Biblioteca;
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
        Jogo jogo = new Jogo();
        Biblioteca biblioteca = new Biblioteca();

        boolean continuar = true;
        while (continuar) {
            System.out.println("[1] Consultar usuário");
            System.out.println("[2] Alterar usuário");
            System.out.println("[3] Excluir usuário");
            System.out.println("[4] Adicionar usuário");
            System.out.println("[5] Consultar jogo");
            System.out.println("[6] Alterar jogo");
            System.out.println("[7] Excluir jogo");
            System.out.println("[8] Adicionar jogo");
            System.out.println("[9] Comprar jogo");
            System.out.println("[10] Consultar jogos de usuário");
            System.out.println("[11] Consultar usuários de jogo");
            System.out.println("[12] Sair");
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
                    System.out.print("Digite o nome do usuário: ");
                    String nome = leitor.nextLine();
                    System.out.print("Digite a senha do usuário: ");
                    String senha = leitor.nextLine();
                    System.out.print("Digite o email do usuário: ");
                    String email = leitor.nextLine();
                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setSenha(senha);
                    usuario.setEmail(email);
                    em.getTransaction().begin();
                    em.persist(usuario);
                    em.getTransaction().commit();
                    System.out.println("Usuário adicionado com sucesso.");
                    System.out.println(separador);
                    break;
                case 5:
                    try {
                        System.out.print("Digite o ID do Jogo: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    jogo = em.find(Jogo.class, id);
                    if (jogo != null) {
                        System.out.println("Jogo encontrado: " + jogo.getTitulo() + " - Preço: " + jogo.getPreco());
                    } else {
                        System.out.println("Jogo não encontrado.");
                    }
                    System.out.println(separador);
                    break;
                case 6:
                    try {
                        System.out.print("Digite o ID do Jogo: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    jogo = em.find(Jogo.class, id);
                    if (jogo != null) {
                        System.out.print("Digite o novo título do jogo: ");
                        String novoTitulo = leitor.nextLine();
                        double novoPreco = 0;
                        try {
                            System.out.print("Digite o novo preço do jogo: ");
                            novoPreco = leitor.nextDouble();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Erro ao ler o preço: " + e.getMessage());
                            leitor.nextLine();
                            System.out.println(separador);
                            continue;
                        }
                        leitor.nextLine();
                        em.getTransaction().begin();
                        jogo.setTitulo(novoTitulo);
                        jogo.setPreco(novoPreco);
                        em.getTransaction().commit();
                        System.out.println("Jogo atualizado com sucesso.");
                    } else {
                        System.out.println("Jogo não encontrado.");
                    }
                    System.out.println(separador);
                    break;
                case 7:
                    try {
                        System.out.print("Digite o ID do Jogo: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    jogo = em.find(Jogo.class, id);
                    if (jogo != null) {
                        em.getTransaction().begin();
                        em.remove(jogo);
                        em.getTransaction().commit();
                        System.out.println("Jogo excluído com sucesso.");
                    } else {
                        System.out.println("Jogo não encontrado.");
                    }
                    System.out.println(separador);
                    break;
                case 8:
                    System.out.print("Digite o Titulo do jogo: ");
                    String titulo = leitor.nextLine();
                    double preco = 0;
                    try {
                        System.out.print("Digite o preco do jogo: ");
                        preco = leitor.nextDouble();
                        leitor.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o preço: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    jogo = new Jogo();
                    jogo.setTitulo(titulo);
                    jogo.setPreco(preco);
                    em.getTransaction().begin();
                    em.persist(jogo);
                    em.getTransaction().commit();
                    System.out.println("Jogo adicionado com sucesso.");
                    System.out.println(separador);
                    break;
                case 9:
                    try {
                        System.out.print("Digite o ID do usuário: ");
                        int usuarioId = leitor.nextInt();
                        System.out.print("Digite o ID do jogo: ");
                        int jogoId = leitor.nextInt();
                        leitor.nextLine();
                        usuario = em.find(Usuario.class, (long) usuarioId);
                        jogo = em.find(Jogo.class, (long) jogoId);
                        if (usuario != null && jogo != null) {
                            biblioteca = new Biblioteca();
                            if(biblioteca.usuarioJaComprouProduto(usuarioId, jogoId)) {
                                System.out.println("Usuário já comprou este jogo.");
                                System.out.println(separador);
                                break;
                            }
                            biblioteca.setUsuario(usuario);
                            biblioteca.setJogo(jogo);
                            em.getTransaction().begin();
                            em.persist(biblioteca);
                            em.getTransaction().commit();
                            System.out.println("Jogo comprado com sucesso.");
                        } else {
                            System.out.println("Usuário ou jogo não encontrado.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler os IDs: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    System.out.println(separador);
                    break;
                case 10:
                    try {
                        System.out.print("Digite o ID do usuário: ");
                        int usuarioId = leitor.nextInt();
                        leitor.nextLine();
                        usuario = em.find(Usuario.class, (long) usuarioId);
                        if (usuario != null) {
                            String jpql = "SELECT b.jogo FROM Biblioteca b WHERE b.usuario.id = :usuarioId";
                            var query = em.createQuery(jpql, Jogo.class);
                            query.setParameter("usuarioId", usuarioId);
                            var jogos = query.getResultList();
                            if (!jogos.isEmpty()) {
                                System.out.println("Jogos comprados pelo usuário " + usuario.getNome() + ":");
                                for (Jogo j : jogos) {
                                    System.out.println("- " + j.getTitulo() + " - Preço: " + j.getPreco());
                                }
                            } else {
                                System.out.println("O usuário não comprou nenhum jogo.");
                            }
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    System.out.println(separador);
                    break;
                case 11:
                    try {
                        System.out.print("Digite o ID do jogo: ");
                        int jogoId = leitor.nextInt();
                        leitor.nextLine();
                        jogo = em.find(Jogo.class, (long) jogoId);
                        if (jogo != null) {
                            String jpql = "SELECT b.usuario FROM Biblioteca b WHERE b.jogo.id = :jogoId";
                            var query = em.createQuery(jpql, Usuario.class);
                            query.setParameter("jogoId", jogoId);
                            var usuarios = query.getResultList();
                            if (!usuarios.isEmpty()) {
                                System.out.println("Usuários que compraram o jogo " + jogo.getTitulo() + ":");
                                for (Usuario u : usuarios) {
                                    System.out.println("- " + u.getNome() + " - Email: " + u.getEmail());
                                }
                            } else {
                                System.out.println("Nenhum usuário comprou este jogo.");
                            }
                        } else {
                            System.out.println("Jogo não encontrado.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Erro ao ler o ID: " + e.getMessage());
                        leitor.nextLine();
                        System.out.println(separador);
                        continue;
                    }
                    System.out.println(separador);
                    break;
                case 12:
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
