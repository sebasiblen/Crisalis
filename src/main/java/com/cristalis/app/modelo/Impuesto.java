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

    public Impuesto() {

    }

    public Impuesto(String descripcion, double porcentaje, Pedido pedido) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }
}
