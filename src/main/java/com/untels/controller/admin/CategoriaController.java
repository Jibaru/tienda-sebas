package com.untels.controller.admin;

import com.untels.service.CategoriaService;
import com.untels.entity.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/admin/categorias")
    public String gestionCategorias(Model model) {
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "admin/categorias/lista-categorias";
    }

    @GetMapping("/admin/categorias/nuevo")
    public String crearCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias/form-categoria";
    }

    @GetMapping("/admin/categorias/{id}")
    public String editarCategoria(@PathVariable("id") int id, Model model) {

        if (!categoriaService.existsByIdCategoria(id)) {
            // TODO: Cambiar a pagina de error
            return "pagina-404";
        }

        model.addAttribute("categoria", categoriaService.findByIdCategoria(id));
        return "admin/categorias/form-categoria";
    }
}
