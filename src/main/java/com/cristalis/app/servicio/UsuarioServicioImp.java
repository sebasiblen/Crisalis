package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.UsuarioRegistroDTO;
import com.cristalis.app.modelo.Rol;
import com.cristalis.app.modelo.Usuario;
import com.cristalis.app.repositorio.UsuarioRepositorio;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author seba
 */
@Service
public class UsuarioServicioImp implements UsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioServicioImp(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(),
                passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getEmail(), Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepositorio.save(usuario);
    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), MapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> MapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

}
