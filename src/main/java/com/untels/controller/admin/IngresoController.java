package com.untels.controller.admin;

import com.untels.service.IngresoService;
import com.untels.entity.Ingreso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IngresoController {

    @Autowired
    IngresoService ingresoService;

    @GetMapping("/admin/ingresos")
    public String gestionIngresos(Model model) {
        model.addAttribute("listaIngresos", ingresoService.findAll());
        return "admin/ingresos/lista-ingresos";
    }

    @GetMapping("/admin/ingresos/nuevo")
    public String crearIngreso(Model model) {
        model.addAttribute("ingreso", new Ingreso());
        return "admin/ingresos/form-ingreso";
    }

}
