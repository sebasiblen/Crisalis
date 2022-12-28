package com.cristalis.app.seguridad;

import com.cristalis.app.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author seba
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        /*
            Hashea texto plano en este caso la pw que ingresa el usuario
        */
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Valida que los datos que recibo son correctos
     * @return 
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        /*
        UserDetailService permite facilitar la busqueda
        y buscar por email en este caso. Y obtener los datos.
        */
        auth.setUserDetailsService(usuarioServicio);
        /*
            Permite guardar la pass codificada
        */
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // Permisos a las rutas que puede acceder
        
        http.authorizeRequests().antMatchers(
                "/registro**",
                "/js/**",
                "/css/**",
                "/img/**").permitAll()
                .anyRequest().authenticated() // autenticamos cualquier otro request
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true) // invalidar request
                .clearAuthentication(true) 
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
        };
    }

}
