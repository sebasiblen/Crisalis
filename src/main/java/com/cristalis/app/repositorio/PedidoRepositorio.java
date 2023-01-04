/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Educacion
 */
@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    
    @Query("SELECT p FROM Pedido p, Persona per "
            + "WHERE (per.apellido LIKE %?1% OR per.dni LIKE %?1%)"
            + " AND p.persona = per.idCliente")
    public List<Pedido> findAll(String cliente);
    
}
