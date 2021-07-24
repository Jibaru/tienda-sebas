package com.untels.controller.tienda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaController {

    @GetMapping("")
    public String principal() {
        return "tienda/index";
    }

    @GetMapping("/inicio-sesion")
    public String inicioSesion() {
        return "tienda/auth/inicio-sesion";
    }
}
