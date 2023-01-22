/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

/**
 *
 * @author Educacion
 */
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
