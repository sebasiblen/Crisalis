/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores.DTO;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Educacion
 */
@Data
public class ClienteRegistroDTO {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;

    private String cuit;
    private String razonSocial;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public ClienteRegistroDTO() {
    }

    public ClienteRegistroDTO(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public ClienteRegistroDTO(String cuit, String razonSocial, Date fecha) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

    public ClienteRegistroDTO(String nombre, String apellido, String dni, String cuit, String razonSocial, Date fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

    public ClienteRegistroDTO(Long idCliente, String nombre, String apellido, String dni, String cuit, String razonSocial, Date fecha) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

}
