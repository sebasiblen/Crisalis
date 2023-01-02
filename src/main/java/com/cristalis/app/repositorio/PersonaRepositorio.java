/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Educacion
 */
@Repository
@Qualifier("persona")
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

//    @Query("SELECT p FROM persona p WHERE p.dni LIKE '%?1%' OR p.nombre LIKE '%?1%'")
//    public List<Persona> findAll(String palabraClave);

}
