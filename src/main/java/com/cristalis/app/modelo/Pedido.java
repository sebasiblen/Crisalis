/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import com.cristalis.app.controladores.DTO.ImpuestoDTO;
import com.cristalis.app.controladores.DTO.ItemDTO;
import static com.cristalis.app.modelo.TipoClienteEnum.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "IVA")
    private double IVA;
    @Column(name = "IIBB")
    private double IIBB;
    @Column(name = "descuento")
    private double descuento;
    @Column(name = "total")
    private double total;
    @Column(name = "fecha_pedido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    // Muchos pedidos corresponden a un cliente (Persona)
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Un pedido le corresponden muchos items
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Item> items;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Impuesto> impuestos;

    public Pedido() {

    }

    public Pedido(Persona persona, List<Item> items) {
        this.fecha = new Date();
        this.persona = persona;
        this.items = items;
        CalcularImpuestosSegunElTipoDelCliente();
        this.descuento = AplicarDescuentos();
        this.subtotal = SubtotalDelPedido();
        Total();
    }

    public Pedido(Empresa empresa, List<Item> items) {
        this.fecha = new Date();
        this.empresa = empresa;
        this.items = items;
        CalcularImpuestosSegunElTipoDelCliente();
        this.descuento = AplicarDescuentos();
        this.subtotal = SubtotalDelPedido();
        Total();
    }

    /**
     * Paso todos los items que le fueron asignados al pedido a DTO. para ver en
     * detalles
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
                dto.setProducto(item.getProducto());
                dto.setServicio(item.getServicio());

                listaDTO.add(dto);
            }
        }

        return listaDTO;
    }

    public List<ImpuestoDTO> CrearDTOImpuestosExtra() {

        List<ImpuestoDTO> listaDto = new ArrayList<>();

        if (!this.impuestos.isEmpty()) {

            for (Impuesto imp : impuestos) {

                ImpuestoDTO dto = new ImpuestoDTO();

                dto.setIdImpuesto(imp.getIdImpuesto());
                dto.setDescripcion(imp.getDescripcion());
                dto.setPorcentaje(imp.getPorcentaje());

                listaDto.add(dto);
            }

        }
        return listaDto;
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
     * Actualiza el subtotal del pedido
     * @return 
     */
    public double ActualizarSubtotalDelPedido() {
        this.subtotal = 0.0;
        for (Item item : items) {
            this.subtotal += item.getSubtotal();
        }
        return this.subtotal;
    }
    
    public void ActualizarDescuentoDelPedido(){
        
        this.descuento = 0.0;
        double topeDesc = CalcularTopeDescuento();
        if (topeDesc <= 2500) {
            this.total -= topeDesc;
        } else {
            this.total -= 2500;
            topeDesc = 2500;
        }
        this.descuento = topeDesc;
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
        if (this.persona != null) {
            if (!this.persona.ServiciosContratados().isEmpty() || isAnyServicioEnOrden()) {
                for (ItemDTO producto : productos) {
                    descuentos += (producto.getSubtotal() * 0.10);
                }
            }
        }

        if (this.empresa != null) {
            if (!this.empresa.ServiciosContratados().isEmpty() || isAnyServicioEnOrden()) {
                for (ItemDTO producto : productos) {
                    descuentos += (producto.getSubtotal() * 0.10);
                }
            }
        }

        return descuentos;
    }

    /**
     * Aplicar el descuento dependiendo del monto.
     *
     * @return
     */
    public double AplicarDescuentos() {

        double descuentoA = CalcularTopeDescuento();

        if (descuentoA <= 2500) {
            this.total -= descuentoA;
        } else {
            this.total -= 2500;
            descuentoA = 2500;
        }
        return descuentoA;
    }

    /**
     * Asignacion de los porcentajes de iva dependiendo el tipo de comprador
     */
    public void CalcularImpuestosSegunElTipoDelCliente() {
        if (this.empresa != null) {
            switch (this.empresa.getTipo()) {
                case EXPORTADOR:
                    this.IVA = 0.37;
                    this.IIBB = 0.035;
                    break;
                case HOTEL_SERVICIO_ALOJAMIENTO_TURISTA:
                    this.IVA = 0.35;
                    this.IIBB = 0.035;
                    break;
                case EXENTO:
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            switch (this.persona.getTipo()) {
                case MONOTRIBUTISTA:
                    this.IVA = 0.27;
                    this.IIBB = 0.035;
                    break;
                case CONSUMIDOR_FINAL:
                    this.IVA = 0.21;
                    this.IIBB = 0.035;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    /**
     * Impuestos extras que se quieran agregar
     */
    public void AgregarImpuestosExtras() {
        for (Impuesto impuesto : impuestos) {
            this.total = this.total + (this.total * impuesto.getPorcentaje());
        }
    }

    /**
     * Costo total del pedido (con IVA y IIBB) despendiendo el tipo de comprador
     *
     * @return
     */
    public double Total() {
        this.total = 0.0;
        for (Item item : items) {

            if (item.getProducto() != null) {
                if (item.getProducto().getTipo_impuesto() == TipoImpuestoEnum.GRAVADO) {
                    this.total += ((item.getProducto().getPrecio()
                            + (item.getProducto().getPrecio() * this.IVA)
                            + (item.getProducto().getPrecio() * this.IIBB)) * item.getUnidades());
                } else {
                    this.total += item.getProducto().getPrecio() * item.getUnidades();
                }

                if (item.getGarantia() > 0) {
                    this.total += (item.getProducto().getPrecio() * 0.02) * item.getGarantia();
                }
            }

            if (item.getServicio() != null) {
                if (item.getServicio().getTipo_impuesto() == TipoImpuestoEnum.GRAVADO) {
                    this.total += ((item.getServicio().getPrecio()
                            + (item.getServicio().getPrecio() * this.IVA)
                            + (item.getServicio().getPrecio() * this.IIBB)
                            + item.getServicio().getMantenimiento()));
                } else {
                    this.total += ((item.getServicio().getPrecio() + item.getServicio().getMantenimiento())
                            * item.getUnidades());
                }
            }
        }

        return this.total;
    }
}
