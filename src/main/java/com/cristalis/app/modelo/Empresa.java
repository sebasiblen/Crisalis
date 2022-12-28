/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class Empresa extends Cliente implements Serializable {

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "iniActividad")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
    
    public Empresa() {
        super();
    }

    public Empresa(String cuit, String razonSocial, Date fecha) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }
    
    

}
