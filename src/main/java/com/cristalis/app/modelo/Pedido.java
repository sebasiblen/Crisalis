/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import com.cristalis.app.controladores.DTO.ItemDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
        CrearDTOdeLosItems();
        this.subtotal = SubtotalDelPedido();
        this.total = PrecioFinalDelPedido();
        this.descuento = AplicarDescuentos();
    }

    /**
     * Paso todos los items que le fueron asignados al pedido a DTO. Devulevo la
     * lista para realizar los calculos usando la lista de DTOS.
     *
     * @return
     */
    public List<ItemDTO> CrearDTOdeLosItems() {

        List<ItemDTO> listaDTO = new ArrayList<>();

        if (!this.items.isEmpty()) {
            for (Item item : this.items) {

                ItemDTO dto = new ItemDTO();
                dto.setIdItem(item.getIdItem());
                dto.setDescripcion(item.getDescripcion());
                dto.setPrecio(item.getPrecio());
                dto.setUnidades(item.getUnidades());
                dto.setGarantia(item.getGarantia());
                dto.setMantenimiento(item.getMantenimiento());
                dto.setSubtotal(item.getSubtotal());
                dto.setIVA(item.getIVA());
                dto.setIIBB(item.getIIBB());
                dto.setTotal(item.getTotal());
                dto.setProducto(item.getProducto());
                dto.setServicio(item.getServicio());

                listaDTO.add(dto);
            }
        }

        return listaDTO;
    }

    /**
     * Devuleve los productos que están en el pedido.
     *
     * @return
     */
    private List<ItemDTO> ProductosEnPedidoActual() {

        List<ItemDTO> productos = new ArrayList<>();
        for (ItemDTO item : CrearDTOdeLosItems()) {
            if (item.getProducto() != null) {
                productos.add(item);
            }
        }

        return productos;
    }

    /**
     * Devuelve el subtotal del pedido.
     *
     * @return
     */
    private double SubtotalDelPedido() {
        double subtotalPedido = 0.0;
        for (ItemDTO item : CrearDTOdeLosItems()) {
            subtotalPedido += item.getSubtotal();
        }
        return subtotalPedido;
    }

    /**
     * Precio TOTAL del pedido.
     *
     * @return
     */
    private double PrecioFinalDelPedido() {
        double precioFinal = 0.0;
        for (ItemDTO item : CrearDTOdeLosItems()) {
            precioFinal += item.getTotal();
        }
        return precioFinal;
    }

    /**
     * Devuelve un boolean si fue solicitado un Servicio en el pedido.
     *
     * @return
     */
    private boolean isAnyServicioEnOrden() {
        for (ItemDTO serv : CrearDTOdeLosItems()) {
            if (serv.getServicio() != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcular el descuento a aplicar.
     *
     * @return
     */
    private double CalcularTopeDescuento() {

        double descuentos = 0.0;
        List<ItemDTO> productos = ProductosEnPedidoActual();

        //SI TIENE SERVICIOS CONTRATADOS PREVIAMENTE
        // Se calcula el descuento que se va a aplicar a los productos.
        if (!this.persona.ServiciosContratados().isEmpty() || isAnyServicioEnOrden()) {
            for (ItemDTO producto : productos) {
                descuentos += (producto.getSubtotal() * 0.10);
            }
        }

        return descuentos;
    }

    /**
     * Aplicar el descuento dependiendo del monto.
     *
     * @return
     */
    private double AplicarDescuentos() {

        double descuentoA = CalcularTopeDescuento();

        if (descuentoA <= 2500) {
            this.total -= descuentoA;
        } else {
            this.total -= 2500;
        }
        return descuentoA;
    }
}
