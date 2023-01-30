/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Educacion
 */
@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Long>{
    
    @Query("SELECT e FROM Empresa e WHERE e.cuit LIKE %?1% OR e.razonSocial LIKE %?1%")
    public List<Empresa> findAll(String palabraClave);
}
