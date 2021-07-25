package com.untels.controller.tienda;

import java.util.ArrayList;
import java.util.List;

import com.untels.entity.Articulo;
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
        List<Articulo> articulos = articuloService.findAll();
        List<Articulo> filtro = new ArrayList<>();
        for (Articulo articulo : articulos) {
            if (articulo.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                filtro.add(articulo);
            }
        }

        model.addAttribute("titulo", "Se buscó: " + busqueda);
        model.addAttribute("listaArticulos", filtro);
        return "tienda/articulos/lista-articulos";
    }

    @GetMapping("/articulos/{id}")
    public String articulo(@PathVariable("id") int id, Model model) {

        if (!articuloService.existePorIdArticulo(id)) {
            // TODO: Cambiar a pagina de error
            return "redirect:/";
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
