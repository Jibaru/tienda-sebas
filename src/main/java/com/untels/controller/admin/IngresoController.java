package com.untels.controller.admin;

import com.untels.service.IngresoService;
import com.untels.service.PersonaService;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;

import com.untels.entity.Ingreso;
import com.untels.entity.Persona;
import com.untels.entity.Usuario;
import com.untels.enums.Rol;
import com.untels.security.supervisor.Supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngresoController {

    @Autowired
    IngresoService ingresoService;

    @Autowired
    PersonaService personaService;

    @Autowired
    HttpSession session;

    @GetMapping("/admin/ingresos")
    public String gestionIngresos(Model model) {
       
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
       
        model.addAttribute("listaIngresos", ingresoService.findAll());
        return "admin/ingresos/lista-ingresos";
    }

    @GetMapping("/admin/ingresos/nuevo")
    public String crearIngreso(Model model) {
        
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
        
        model.addAttribute("ingreso", new Ingreso());
        model.addAttribute("listaPersonas", personaService.findAll());
        return "admin/ingresos/form-ingreso";
    }

    @GetMapping("/admin/ingresos/grabar")
    public String grabarIngreso(@ModelAttribute("ingreso") Ingreso ingresoParam) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
        
        Usuario gerenteUsuario = (Usuario) session.getAttribute("usuario");
        Persona gerente = personaService.findByIdPersona(gerenteUsuario.getPersona().getIdPersona());
        Persona proveedor = personaService.findByIdPersona(ingresoParam.getProveedor().getIdPersona());
        Ingreso ingreso = new Ingreso();

        ingreso.setTipoComprobante(ingresoParam.getTipoComprobante());
        ingreso.setSerieComprobante(ingresoParam.getSerieComprobante());
        ingreso.setNumComprobante(ingresoParam.getNumComprobante());
        ingreso.setFecha(new GregorianCalendar());
        ingreso.setImpuesto(ingresoParam.getImpuesto());
        ingreso.setTotal(ingresoParam.getTotal());
        ingreso.setEstado(ingresoParam.getEstado());
        ingreso.setProveedor(proveedor);
        ingreso.setGerente(gerente);
        ingresoService.save(ingreso);

        return  "redirect:/admin/ingresos";
    }

}
