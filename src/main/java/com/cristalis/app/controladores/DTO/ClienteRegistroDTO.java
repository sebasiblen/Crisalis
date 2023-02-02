package com.cristalis.app.controladores.DTO;

import com.cristalis.app.modelo.TipoClienteEnum;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ClienteRegistroDTO implements Serializable {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String cuit;
    private String razonSocial;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    @Enumerated(value = EnumType.STRING)
    private TipoClienteEnum tipo;

    public ClienteRegistroDTO() {
    }

    public ClienteRegistroDTO(String nombre, String apellido, String dni, TipoClienteEnum tipo_cliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tipo = tipo_cliente;
    }

    public ClienteRegistroDTO(TipoClienteEnum tipo_cliente, String cuit, String razonSocial, Date fecha) {
        this.tipo = tipo_cliente;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

    public ClienteRegistroDTO(String nombre, String apellido, String dni, TipoClienteEnum tipo_cliente, String cuit, String razonSocial, Date fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tipo = tipo_cliente;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

    public ClienteRegistroDTO(Long idCliente, String nombre, String apellido, String dni, TipoClienteEnum tipo_cliente, String cuit, String razonSocial, Date fecha) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tipo = tipo_cliente;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

}
