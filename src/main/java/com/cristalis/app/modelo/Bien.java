package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Bien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBien;

    private String descripcion;
    private Double precio;
    @Column(name = "tipo_impuesto", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoImpuestoEnum tipo_impuesto;

    public Bien() {

    }

    public Bien(String descripcion, Double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Bien(String descripcion, Double precio, TipoImpuestoEnum tipo_impuesto) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo_impuesto = tipo_impuesto;
    }

}
