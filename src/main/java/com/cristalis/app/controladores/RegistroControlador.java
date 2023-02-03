package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Producto;
import com.cristalis.app.servicio.ProductoServicio;
import com.cristalis.app.servicio.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {

    @Autowired
    private UsuarioServicio servicio;
    
    @Autowired
    private ProductoServicio productoServicio;
    
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo) {
//        modelo.addAttribute("usuarios", servicio.listarUsuarios());
        List<String> nombres = new ArrayList<>();
        List<Integer> stock = new ArrayList<>();
        for (Producto prod : productoServicio.listadoProductos()) {
            nombres.add(prod.getDescripcion());
            stock.add(prod.getStock());
        }
        modelo.addAttribute("nombres", nombres);
        modelo.addAttribute("stock", stock);
        return "index";
    }
}
