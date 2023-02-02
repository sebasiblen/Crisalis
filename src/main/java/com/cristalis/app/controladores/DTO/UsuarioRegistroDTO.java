package com.cristalis.app.controladores.DTO;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioRegistroDTO implements Serializable {

    private Long id;
    private String nombre;
    private String apellido;
    private String password;
    private String email;

    public UsuarioRegistroDTO() {
    }

    public UsuarioRegistroDTO(String email) {
        this.email = email;
    }

    public UsuarioRegistroDTO(String nombre, String apellido, String password, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
    }

    public UsuarioRegistroDTO(Long id, String nombre, String apellido, String password, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
    }

}
