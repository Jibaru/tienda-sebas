package com.untels.controller.tienda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.untels.dto.compras.CarritoArticuloDTO;
import com.untels.dto.compras.CarritoCompraDTO;
import com.untels.entity.Articulo;
import com.untels.entity.Categoria;
import com.untels.service.ArticuloService;
import com.untels.service.CategoriaService;

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

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    HttpSession session;

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
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "tienda/articulos/lista-articulos";
    }

    @GetMapping("/articulos/{id}")
    public String articulo(@PathVariable("id") int id, Model model) {

        if (!articuloService.existePorIdArticulo(id)) {
            return "redirect:/";
        }

        CarritoCompraDTO.Dato datos = new CarritoCompraDTO.Dato();
        datos.setIdArticulo(id);
        datos.setCantidad(1);

        @SuppressWarnings("unchecked")
        List<CarritoArticuloDTO> carrito = (List<CarritoArticuloDTO>) session.getAttribute("carrito");

        int agregado = 0;
        if (carrito != null) {
            for (int i = 0; agregado == 0 && i < carrito.size(); i++) {
                if (carrito.get(i).getIdArticulo() == id) {
                    agregado = 1;
                }
            }
        }

        model.addAttribute("datos", datos);
        model.addAttribute("agregado", agregado);
        model.addAttribute("articulo", articuloService.findByIdArticulo(id));
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "tienda/articulos/ver-articulo";
    }

    @GetMapping("/articulos/categoria/{id}")
    public String articulosPorCategoria(@PathVariable("id") int id, Model model) {

        Categoria categoria = categoriaService.findByIdCategoria(id);
        List<Articulo> articulos = articuloService.findAll();
        List<Articulo> filtro = new ArrayList<>();
        for (Articulo articulo : articulos) {
            if (articulo.getCategoria().getIdCategoria() == id) {
                filtro.add(articulo);
            }
        }

        model.addAttribute("titulo", "Artículos con categoría: " + categoria.getNombre());
        model.addAttribute("listaArticulos", filtro);
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "tienda/articulos/lista-articulos";
    }
}
