/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.repositorio.PedidoRepositorio;
import com.cristalis.app.repositorio.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IPedidoServicio implements PedidoServicio {

    @Autowired
    private PedidoRepositorio repositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Override
    public List<Pedido> listadoPedidos() {
        return repositorio.findAll();
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return repositorio.save(pedido);
    }

    @Override
    public Pedido obtenerPedidoPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarPedido(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Pedido> pedidoDiscriminadoPorCliente(String cliente) {
        return repositorio.sp_pedidos_disc_cliente(cliente);
    }

    @Override
    public List<Pedido> pedidoDiscriminadoPorServicio(String servicio) {
        return repositorio.sp_pedidos_disc_servicio(servicio);
    }

    @Override
    public List<Pedido> pedidoDiscriminadoPorProducto(String producto) {
        return repositorio.sp_pedidos_disc_producto(producto);
    }

    @Override
    public List<Pedido> pedidoAsociadoAlCliente(String cliente) {
        return repositorio.sp_pedidos_asociados(cliente);
    }

}
