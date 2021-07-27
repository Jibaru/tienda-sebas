package com.untels.controller.admin;

import com.untels.service.ArticuloService;
import com.untels.service.CategoriaService;

import javax.servlet.http.HttpSession;

import com.untels.entity.Articulo;
import com.untels.entity.Categoria;
import com.untels.enums.Rol;
import com.untels.security.supervisor.Supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @Autowired
    HttpSession session;

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/admin/categorias")
    public String gestionCategorias(Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "admin/categorias/lista-categorias";
    }

    @GetMapping("/admin/categorias/nuevo")
    public String crearCategoria(Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
      
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "admin/categorias/form-categoria";
    }

    @PostMapping("/admin/categorias/grabar")
    public String grabarCategoria(@ModelAttribute("categoria") Categoria categoriaParam) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        Categoria categoria = new Categoria();

        categoria.setNombre(categoriaParam.getNombre());
        categoria.setDescripcion(categoriaParam.getDescripcion());
        categoria.setEstado(categoriaParam.getEstado());

        categoriaService.save(categoria);

        return  "redirect:/admin/categorias";
    }

    @GetMapping("/admin/categorias/{id}")
    public String editarCategoria(@PathVariable("id") int id, Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        if (!categoriaService.existsByIdCategoria(id)) {
            // TODO: Cambiar a pagina de error
            return "pagina-404";
        }

        model.addAttribute("categoria", categoriaService.findByIdCategoria(id));
        return "admin/categorias/form-categoria";
    }

    @PostMapping("/admin/categorias/editar")
    public String editarCategoriaForm(@ModelAttribute("categoria") Categoria categoriaParam) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        Categoria categoria = categoriaService.findByIdCategoria(categoriaParam.getIdCategoria());

        categoria.setNombre(categoriaParam.getNombre());
        categoria.setDescripcion(categoriaParam.getDescripcion());
        categoria.setEstado(categoriaParam.getEstado());

        categoriaService.save(categoria);

        return  "redirect:/admin/categorias";
    }

    @GetMapping("/admin/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") int id) {
      
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        } 
        
        Categoria categoria = categoriaService.findByIdCategoria(id);
        for(Articulo articulo:categoria.getArticulos()){
            articulo.setCategoria(null);
            articuloService.save(articulo);
        }

        categoriaService.deleteByIdCategoria(id);
        return  "redirect:/admin/categorias";
    }
}
