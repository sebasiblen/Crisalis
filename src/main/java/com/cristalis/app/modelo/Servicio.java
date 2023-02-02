package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Servicio extends Bien implements Serializable {

    private double mantenimiento;
    
    public Servicio() {
    }

    public Servicio(String descripcion, double precio, double mantenimiento) {
        super(descripcion, precio);
        this.mantenimiento = mantenimiento;
    }

    public Servicio(double mantenimiento, String descripcion, Double precio, TipoImpuestoEnum tipo_impuesto) {
        super(descripcion, precio, tipo_impuesto);
        this.mantenimiento = mantenimiento;
    }

}