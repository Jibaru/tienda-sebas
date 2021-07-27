package com.untels.controller.admin;

import javax.servlet.http.HttpSession;

import com.untels.entity.Venta;
import com.untels.enums.EstadoVenta;
import com.untels.enums.Rol;
import com.untels.security.supervisor.Supervisor;
import com.untels.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/admin/ventas/cancelar/{id}")
    public String cancelarVenta(@PathVariable("id") long id, Model model) {

        if (!ventaService.existsByIdVenta(id)) {
            return "redirect:/admin/ventas";
        }

        Venta venta = ventaService.findByIdVenta(id);
        venta.setEstado(EstadoVenta.ANULADO);
        ventaService.save(venta);

        return "redirect:/admin/ventas";
    }

    @GetMapping("/admin/ventas/completar/{id}")
    public String completarVenta(@PathVariable("id") long id, Model model) {

        if (!ventaService.existsByIdVenta(id)) {
            return "redirect:/admin/ventas";
        }

        Venta venta = ventaService.findByIdVenta(id);
        venta.setEstado(EstadoVenta.COMPLETADO);
        ventaService.save(venta);

        return "redirect:/admin/ventas";
    }

}
