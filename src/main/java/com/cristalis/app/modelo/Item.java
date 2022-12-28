/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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

    // Muchos items corresponen a un pedido
    @ManyToOne
    private Pedido pedido;

    // Un item correspone a un producto
    @OneToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "idBien")
    private Producto producto;

    // Un item corresponde a un servicio
    @OneToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "idBien")
    private Servicio servicio;

    public Item() {
        this.unidades = 1;
        this.IVA = 0.21;
        this.IIBB = 0.035;
    }

}
