/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
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
    
    @Procedure
    public List<Pedido> sp_pedidos_disc_cliente(@Param("palabra")String cliente);
    
    @Procedure
    public List<Pedido> sp_pedidos_disc_servicio(@Param("palabra")String servicio);
    
    @Procedure
    public List<Pedido> sp_pedidos_disc_producto(@Param("palabra")String producto);
    
    // discriminados por fechas
    
}
