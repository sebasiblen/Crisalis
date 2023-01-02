/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    protected List<Servicio> ServiciosContratados(){
        
        List<Servicio> servicios = new ArrayList<>();
        
        for (Pedido pedido : pedidos) {
            for (Item item : pedido.getItems()) {
                if(item.getServicio() != null){
                    servicios.add(item.getServicio());
                }
            }
        }
        
        return servicios;
    }

}
