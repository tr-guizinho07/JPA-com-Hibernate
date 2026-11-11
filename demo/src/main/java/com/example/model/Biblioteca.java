package com.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;

@Entity
@Table (name = "biblioteca")
@IdClass (BibliotecaId.class)
public class Biblioteca {

    @Id
    @ManyToOne
    @JoinColumn (name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn (name = "id_jogo", referencedColumnName = "id")
    private Jogo jogo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public boolean usuarioJaComprouProduto(int usuarioId, int jogoId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("plataforma-jogos");
        EntityManager em = emf.createEntityManager();
        String jpql = "select * from biblioteca b where b.id_usuario = ? and b.id_jogo = ?";
        var query = em.createNativeQuery(jpql);
            query.setParameter(1, usuarioId);
            query.setParameter(2, jogoId);
            var result = query.getResultList();
        return !result.isEmpty();
    }
}
