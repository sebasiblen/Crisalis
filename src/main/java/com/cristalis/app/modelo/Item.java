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
    @Column(name = "estado")
    private String estado;
    // Un item correspone a un producto
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", referencedColumnName = "idBien")
    private Producto producto;

    // Un item corresponde a un servicio
    @OneToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "idBien")
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    // constructores
    public Item() {
        this.unidades = 1;
    }

    public Item(Producto producto) {
        this.producto = producto;
    }

    public Item(Servicio servicio) {
        this.servicio = servicio;
    }

    public Item(String descripcion, double precio, int unidades, int garantia, Producto producto) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades = unidades;
        this.garantia = garantia;
        this.producto = producto;
    }

    public Item(String descripcion, double precio, double mantenimiento, Servicio servicio) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.mantenimiento = mantenimiento;
        this.servicio = servicio;
    }

}
