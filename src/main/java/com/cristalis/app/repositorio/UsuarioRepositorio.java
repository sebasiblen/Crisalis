package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author seba
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     *
     * @param email
     * @return
     */
    public Usuario findByEmail(String email);
}
