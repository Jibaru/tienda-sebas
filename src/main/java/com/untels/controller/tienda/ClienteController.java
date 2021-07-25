package com.untels.controller.tienda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    // Los datos del usuario los sacas de la sesion
    @GetMapping("/perfil")
    public String perfil() {
        return "tienda/perfil/ver-perfil";
    }
}
