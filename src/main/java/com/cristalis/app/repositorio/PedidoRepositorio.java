package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            select * from pedido p, item i 
            where i.descripcion LIKE '%'+ '100' + '%'
            and i.pedido_id = p.id_pedido
            and i.id_servicio > 0
        END
     */
    @Procedure
    public List<Pedido> sp_pedidos_disc_servicio(@Param("palabra") String servicio);

    /*
        CREATE PROC sp_pedidos_disc_producto
        @palabra VARCHAR (255)
        AS 
        BEGIN
           select * from pedido p, item i 
           where i.descripcion LIKE '%'+ 'Tec' + '%'
           and i.pedido_id = p.id_pedido
           and i.id_producto > 0
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
