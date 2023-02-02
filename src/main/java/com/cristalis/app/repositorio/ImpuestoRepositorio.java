package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Impuesto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestoRepositorio extends JpaRepository<Impuesto, Long> {
    
    @Procedure
    public List<Impuesto> sp_impuesto_descripcion(@Param("palabra")String palabra);
}
