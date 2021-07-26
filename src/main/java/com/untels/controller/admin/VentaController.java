package com.untels.controller.admin;

import javax.servlet.http.HttpSession;

import com.untels.enums.Rol;
import com.untels.security.supervisor.Supervisor;
import com.untels.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VentaController {

    @Autowired
    VentaService ventaService;

    @Autowired
    HttpSession session;

    @GetMapping("/admin/ventas")
    public String gestionVentas(Model model) {
        
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
        
        model.addAttribute("listaVentas", ventaService.findAll());
        return "admin/ventas/lista-ventas";
    }

}
