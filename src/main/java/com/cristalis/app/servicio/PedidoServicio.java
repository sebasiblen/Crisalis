/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Pedido;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface PedidoServicio {
    
    public List<Pedido> listadoPedidos();
    
    public Pedido guardarPedido(Pedido pedido);
    
    public Pedido obtenerPedidoPorID(Long id);
    
    public void eliminarPedido(Long id);
}
