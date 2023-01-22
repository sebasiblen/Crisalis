/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Id
    private Long idItem;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private double precio;
    @Column(name = "unidades")
    private int unidades;
    @Column(name = "garantia")
    private int garantia;
    @Column(name = "mantenimiento")
    private double mantenimiento;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "IVA")
    private double IVA;
    @Column(name = "IIBB")
    private double IIBB;
    @Column(name = "total")
    private double total;

    // Un item correspone a un producto
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", referencedColumnName = "idBien")
    private Producto producto;

    // Un item corresponde a un servicio
    @OneToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "idBien")
    private Servicio servicio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
    
    
    // constructores
    public Item() {
        this.unidades = 1;
        this.IIBB = 0.035;
    }

    public Item(Producto producto) {
        this.producto = producto;
        this.IIBB = 0.035;
        this.subtotal = SubTotal();
    }

    public Item(Servicio servicio) {
        this.servicio = servicio;
        this.IIBB = 0.035;
        this.subtotal = SubTotal();
    }
    
    
    /**
     * Subtotales de los productos , sin(IMPUESTOS)
     * @return 
     */
    public double SubTotal() {
        if (this.producto != null) {
            this.subtotal += (this.producto.getPrecio() * this.unidades);
        }
        if (this.servicio != null) {
            this.subtotal += (this.servicio.getPrecio() + this.servicio.getMantenimiento()) * this.unidades;
        }
        return this.subtotal;
    }
    
    
    public double Total() {
        if (this.producto != null
                && this.producto.getTipo_impuesto()==TipoImpuestoEnum.GRAVADO) {
            this.total += ((this.producto.getPrecio()
                    + (this.producto.getPrecio() * this.IVA)
                    + (this.producto.getPrecio() * this.IIBB)) * this.unidades);
            if (this.garantia > 0) {
                this.total += (this.producto.getPrecio() * 0.02) * this.garantia;
            }
        }
        if (this.servicio != null
                && this.servicio.getTipo_impuesto()==TipoImpuestoEnum.GRAVADO) {
            this.total += ((this.servicio.getPrecio()
                    + (this.servicio.getPrecio() * this.IVA)
                    + (this.servicio.getPrecio() * this.IIBB)
                    + this.servicio.getMantenimiento()) * this.unidades);
        }
        return this.total;
    }
}
