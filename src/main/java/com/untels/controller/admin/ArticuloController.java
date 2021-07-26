package com.untels.controller.admin;

import com.untels.service.ArticuloService;

import javax.servlet.http.HttpSession;

import com.untels.entity.Articulo;
import com.untels.enums.Rol;
import com.untels.security.supervisor.Supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    HttpSession session;

    @GetMapping("/admin/articulos")
    public String gestionArticulos(Model model) {
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        model.addAttribute("listaArticulos", articuloService.findAll());
        return "admin/articulos/lista-articulos";
    }

    @GetMapping("/admin/articulos/nuevo")
    public String crearArticulo(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "admin/articulos/form-articulo";
    }

    @GetMapping("/admin/articulo/{id}")
    public String editarArticulo(@PathVariable("id") int id, Model model) {

        if (!articuloService.existePorIdArticulo(id)) {
            // TODO: Cambiar a pagina de error
            return "pagina-404";
        }

        model.addAttribute("articulo", articuloService.findByIdArticulo(id));
        return "admin/articulos/form-articulo";
    }

    @GetMapping("/admin/articulos/eliminar/{id}")
    public String eliminarArticulo(@PathVariable("id") int id) {
        articuloService.deleteByIdArticulo(id);
        return  "redirect:/admin/articulos";
    }
}
