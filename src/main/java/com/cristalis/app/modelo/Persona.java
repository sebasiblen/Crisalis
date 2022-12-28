/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class Persona extends Cliente {

    @Column(name = "nombre")
    private String nombre;
    

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Pedido> pedidos;

    public Persona() {
        super();
    }

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

}
