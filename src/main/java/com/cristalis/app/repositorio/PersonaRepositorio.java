
package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Educacion
 */
@Repository
@Qualifier("persona")
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    // OR p.nombre LIKE %?1%
    @Query("SELECT p FROM Persona p WHERE p.dni LIKE %?1% OR p.apellido LIKE %?1%")
    public List<Persona> findAll(String palabraClave);

}
