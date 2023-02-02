package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    @Procedure
    public List<Producto> sp_producto_descripcion(@Param("palabra") String palabra);
}
