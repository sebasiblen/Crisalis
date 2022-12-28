/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import java.util.List;
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
    
    
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private double precio;
    @Column(name = "unidades")
    private int unidades;
    @Column(name = "IVA")
    private double IVA;
    @Column(name = "IIBB")
    private double IIBB;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "descuento")
    private double descuento;
    @Column(name = "total")
    private double total;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "estado")
    private String estado;
    
    // Muchos pedidos corresponden a un cliente (Persona)
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona personas;
    
    // Un pedido le corresponden muchos items
    @OneToMany
    private List<Item> items;
    
    public Pedido() {
    }

}
