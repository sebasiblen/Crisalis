/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @OneToMany(mappedBy = "persona")
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

    public List<Servicio> ServiciosContratados() {

        List<Servicio> servicios = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            
            for (Item item : pedido.getItems()) {
                if (item.getServicio() != null) {
                    servicios.add(item.getServicio());
                }
            }
            
        }
        
        return servicios;
    }

    public List<Servicio> VencimientoServicio() {
       
        List<Servicio> serviciosVencidos = new ArrayList<>();
        
        Date fechaActual = new Date();
        
        for (Pedido pedido : pedidos) {
            Date fechaPedido = pedido.getFecha();
            
            long dif = fechaActual.getTime() - fechaPedido.getTime();
            
            if (dif > 31) {
                
                for (Item item : pedido.getItems()) {
                    if (item.getServicio() != null) {
                        
                        serviciosVencidos.add(item.getServicio());
                    }
                }
                
            }
        }
        
        return serviciosVencidos;
    }
}
