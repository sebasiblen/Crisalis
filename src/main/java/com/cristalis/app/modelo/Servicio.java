/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class Servicio extends Bien implements Serializable {

    private double mantenimiento;

    // Un servicio se corresponde con un item
    @OneToOne
    private Item item;

    public Servicio() {
    }

    public Servicio(String descripcion, double precio, double mantenimiento) {
        super(descripcion, precio);
        this.mantenimiento = mantenimiento;
    }

}
