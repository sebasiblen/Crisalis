package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ImpuestoDTO;
import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Pedido;
import java.util.List;

public interface PedidoServicio {
    
    public List<Pedido> listadoPedidos();
    
    public List<Pedido> pedidoAsociadoAlCliente(String cliente);
    
    public List<Pedido> pedidoDiscriminadoPorCliente(String cliente);
    
    public List<Pedido> pedidoDiscriminadoPorServicio(String servicio);
    
    public List<Pedido> pedidoDiscriminadoPorProducto(String producto);
    
    public Pedido guardarPedido(Pedido pedido);
    
    public Pedido obtenerPedidoPorID(Long id);
    
    public void eliminarPedido(Long id);
    
    public List<ItemDTO> crearDTOdeLosItems(Pedido pedido);
    
    public List<ImpuestoDTO> crearDTOImpuestosExtra(Pedido pedido);
    
    public List<ItemDTO> productosEnPedidoActual(Pedido pedido);
    
    public void subtotalDelPedido(Pedido pedido);
    
    public void actualizarSubtotalDelPedido(Pedido pedido);
    
    public void actualizarDescuentoDelPedido(Pedido pedido);
    
    public boolean estaAlgunServicioEnOrden(Pedido pedido);
    
    public double calcularTopeDescuento(Pedido pedido);
    
    public void aplicarDescuentos(Pedido pedido);
    
    public void CalcularImpuestosSegunElTipoDelCliente(Pedido pedido);
    
    public void AgregarImpuestosExtras(Pedido pedido);
    
    public void Total(Pedido pedido);
}
