/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores.DTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Educacion
 */
@Data
public class ImpuestoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImpuesto;
    private String descripcion;
    private double porcentaje;

    public ImpuestoDTO() {
    }

    public ImpuestoDTO(String descripcion, double porcentaje) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

    public ImpuestoDTO(Long idImpuesto, String descripcion, double porcentaje) {
        this.idImpuesto = idImpuesto;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

}
