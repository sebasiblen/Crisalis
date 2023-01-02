/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @javax.persistence.Id
    private Long idPedido;

    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "descuento")
    private double descuento;
    @Column(name = "total")
    private double total;

    // Muchos pedidos corresponden a un cliente (Persona)
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    // Un pedido le corresponden muchos items
    @OneToMany(mappedBy = "pedido")
    private List<Item> items;

    public Pedido() {
        
    }

    public Pedido(Persona persona, List<Item> items) {
        this.persona = persona;
        this.items = items;
//        this.subtotal = SubtotalDelPedido();
//        this.total = PrecioFinalDelPedido();
        this.items = items;
//        this.descuento = DescuentoSobreProductos();
    }
    
    
    /**
     * SUBTOTAL DEL PEDIDO INCLUYE LOS COSTOS DE MANTENIMIENTO DE LOS SERVICIOS
     */
//    private double SubtotalDelPedido() {
//        double subtotalPedido = 0.0;
//        for (Item item : items) {
//            subtotalPedido += item.getSubtotal();
//        }
//        return subtotalPedido;
//    }
//
//    // EL PRECIO TOTAL DEL PEDIDO
//    private double PrecioFinalDelPedido() {
//        double precioFinal = 0.0;
//        for (Item item : items) {
//            precioFinal += item.Total();
//        }
//        return precioFinal;
//    }
//
//    //PRODUCTOS DEL PEDIDO ACTUAL.
//    private List<Producto> ProductosEnPedidoActual() {
//
//        List<Producto> productos = new ArrayList<>();
//        for (Item item : items) {
//            if (item.getProducto() != null) {
//                productos.add(item.getProducto());
//            }
//        }
//
//        return productos;
//    }
//
//    private double DescuentoSobreProductos() {
//        double descuentoAplicado = 0.0;
//
//        List<Producto> productos = ProductosEnPedidoActual();
//
//        //SI TIENE SERVICIOS CONTRATADOS PREVIAMENTE
//        if (!this.persona.ServiciosContratados().isEmpty()) {
//
//            for (Producto producto : productos) {
//                descuentoAplicado += producto.getPrecio() * 0.10;
//            }
//        }
//
//        // Aplica descuento sobre cada producto de este pedido
//        if (descuentoAplicado <= 2500) {
//            for (Producto producto : productos) {
//                double nuevoPrecio = producto.getPrecio() - (producto.getPrecio() * 0.10);
//                producto.setPrecio(nuevoPrecio);
//            }
//        }
//
//        return descuentoAplicado;
//    }

}
