package com.untels.controller.tienda;

import com.untels.service.ArticuloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BusquedaArticulosController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/articulos")
    public String articulos(@ModelAttribute("busqueda") String busqueda, Model model) {
        // TODO: usar el parametro de busqueda
        model.addAttribute("articulos", articuloService.findAll());
        return "tienda/articulos/lista-articulos";
    }

    @GetMapping("/articulo/{id}")
    public String articulo(@PathVariable("id") int id, Model model) {

        if (!articuloService.existePorIdArticulo(id)) {
            // TODO: Cambiar a pagina de error
            return "pagina-404";
        }

        model.addAttribute("articulo", articuloService.findByIdArticulo(id));
        return "tienda/articulos/ver-articulo";
    }

    @GetMapping("/articulos/categoria/{id}")
    public String articulosPorCategoria(@PathVariable("id") int id, Model model) {
        // TODO: Filtrar por categoría
        model.addAttribute("listaArticulos", articuloService.findAll());
        return "tienda/articulos/lista-articulos";
    }
}
