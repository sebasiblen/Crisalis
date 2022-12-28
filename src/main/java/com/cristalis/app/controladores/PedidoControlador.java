/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Educacion
 */
@Controller
public class PedidoControlador {
    
    @GetMapping("/pedidos")
    public String Pedidos(){
        return "pedidos";
    }
    
}
