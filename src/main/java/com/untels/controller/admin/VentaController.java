package com.untels.controller.admin;

import com.untels.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VentaController {

    @Autowired
    VentaService ventaService;

    @GetMapping("/admin/ventas")
    public String gestionVentas(Model model) {
        model.addAttribute("listaVentas", ventaService.findAll());
        return "admin/ventas/lista-ventas";
    }

}
