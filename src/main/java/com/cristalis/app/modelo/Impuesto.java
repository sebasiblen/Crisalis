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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class Impuesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImpuesto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "porcentaje")
    private double porcentaje;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Impuesto() {

    }

    public Impuesto(String descripcion, double porcentaje, Pedido pedido) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }
    
    public void setPorcentaje(double porcentaje){
        if (porcentaje > 0) {
            this.porcentaje = (porcentaje / 100);
        }
        this.porcentaje = porcentaje;
    }
}
