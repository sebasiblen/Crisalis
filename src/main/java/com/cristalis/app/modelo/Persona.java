/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Educacion
 */
@Data
@Entity
@DiscriminatorValue("Persona")
public class Persona extends Cliente implements Serializable {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;

    @Column(name = "tipo_comprador", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoClienteEnum tipo;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;

    public Persona() {
        super();
    }

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Persona(String nombre, String apellido, String dni, TipoClienteEnum tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tipo = tipo;
    }

}
