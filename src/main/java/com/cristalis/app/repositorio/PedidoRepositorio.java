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

    /*
        CREATE PROC sp_pedidos_disc_cliente
        @palabra VARCHAR (255)
        AS
        BEGIN
            select * from pedido p, cliente c
            where (c.apellido like '%'+ @palabra + '%' AND c.id_cliente = p.persona_id)
            OR (c.razon_social like '%'+ @palabra +'%' AND c.id_cliente = p.empresa_id)
        END
     */
    @Procedure
    public List<Pedido> sp_pedidos_disc_cliente(@Param("palabra") String cliente);

    /*
        CREATE PROC sp_pedidos_disc_servicio
        @palabra VARCHAR (255)
        AS 
        BEGIN
            SELECT * FROM pedido p, item i, servicio s
WHERE (i.id_servicio = s.id_bien AND p.id_pedido = i.pedido_id AND s.descripcion LIKE '%'+'in'+'%')
        END
     */
    @Procedure
    public List<Pedido> sp_pedidos_disc_servicio(@Param("palabra") String servicio);

    /*
        CREATE PROC sp_pedidos_disc_producto
        @palabra VARCHAR (255)
        AS 
        BEGIN
            select * from pedido p, item i, producto prod
        where ((prod.descripcion like '%'+ 'er' + '%')AND (i.id_producto > 0 and i.pedido_id = p.id_pedido))
        END
     */
    @Procedure
    public List<Pedido> sp_pedidos_disc_producto(@Param("palabra") String producto);

    /*
        CREATE PROC sp_pedidos_asociados
            @palabra VARCHAR (255)
        AS
        BEGIN
            select *
            from pedido p
            where p.empresa_id = @palabra
            OR p.persona_id = @palabra
        END
    */
    @Procedure
    public List<Pedido> sp_pedidos_asociados(@Param("palabra") String cliente);
}
