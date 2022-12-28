/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.repositorio;

import com.cristalis.app.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Educacion
 */
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}
