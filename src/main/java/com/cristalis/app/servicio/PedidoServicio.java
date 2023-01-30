/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Pedido;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface PedidoServicio {
    
    public List<Pedido> listadoPedidos();
    
    public List<Pedido> pedidoAsociadoAlCliente(String cliente);
    
    public List<Pedido> pedidoDiscriminadoPorCliente(String cliente);
    
    public List<Pedido> pedidoDiscriminadoPorServicio(String servicio);
    
    public List<Pedido> pedidoDiscriminadoPorProducto(String producto);
    
    public Pedido guardarPedido(Pedido pedido);
    
    public Pedido obtenerPedidoPorID(Long id);
    
    public void eliminarPedido(Long id);
}
