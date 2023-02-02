package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {

    @Procedure
    public List<Servicio> sp_servicio_descripcion(@Param("palabra") String palabra);
}
