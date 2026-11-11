package com.example.model;

import java.util.Objects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;

public class BibliotecaId implements Serializable {
    private Long usuario;
    private Long jogo;

    public BibliotecaId() {
    }

    public BibliotecaId(Long usuarioId, Long jogoId) {
        this.usuario = usuarioId;
        this.jogo = jogoId;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getJogo() {
        return jogo;
    }

    public void setJogo(Long jogo) {
        this.jogo = jogo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliotecaId that = (BibliotecaId) o;
        return usuario.equals(that.usuario) && jogo.equals(that.jogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, jogo);
    }
}
