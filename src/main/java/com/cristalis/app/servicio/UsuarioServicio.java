package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.UsuarioRegistroDTO;
import com.cristalis.app.modelo.Usuario;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
}
