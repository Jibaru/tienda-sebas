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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    HttpSession session;

    @GetMapping("/admin/articulos")
    public String gestionArticulos(Model model) {
        /*
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }*/

        model.addAttribute("listaArticulos", articuloService.findAll());
        return "admin/articulos/lista-articulos";
    }

    @GetMapping("/admin/articulos/nuevo")
    public String crearArticulo(Model model) {
        model.addAttribute("articulo", new Articulo());
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "admin/articulos/form-articulo";
    }

    @PostMapping("/admin/articulos/grabar")
    public String grabarPersona(@ModelAttribute("articulo") Articulo articuloParam) {

        Articulo articulo = new Articulo();
        Categoria categoria = categoriaService.findByIdCategoria(articuloParam.getCategoria().getIdCategoria());

        articulo.setCodigo(articuloParam.getCodigo());
        articulo.setNombre(articuloParam.getNombre());
        articulo.setPrecioVenta(articuloParam.getPrecioVenta());
        articulo.setStock(articuloParam.getStock());
        articulo.setDescripcion(articuloParam.getDescripcion());
        articulo.setImagen(articuloParam.getImagen());
        articulo.setEstado(articuloParam.getEstado());
        articulo.setCategoria(categoria);
    
        articuloService.save(articulo);

        return  "redirect:/admin/articulos";
    }

    @GetMapping("/admin/articulo/{id}")
    public String editarArticulo(@PathVariable("id") int id, Model model) {

        if (!articuloService.existePorIdArticulo(id)) {
            // TODO: Cambiar a pagina de error
            return "pagina-404";
        }

        model.addAttribute("articulo", articuloService.findByIdArticulo(id));
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "admin/articulos/form-articulo";
    }

    @PostMapping("/admin/articulos/editar")
    public String editarArticuloForm(@ModelAttribute("articulo") Articulo articuloParam) {

        Articulo articulo = articuloService.findByIdArticulo(articuloParam.getIdArticulo());
        Categoria categoria = categoriaService.findByIdCategoria(articuloParam.getCategoria().getIdCategoria());

        articulo.setCodigo(articuloParam.getCodigo());
        articulo.setNombre(articuloParam.getNombre());
        articulo.setPrecioVenta(articuloParam.getPrecioVenta());
        articulo.setStock(articuloParam.getStock());
        articulo.setDescripcion(articuloParam.getDescripcion());
        articulo.setImagen(articuloParam.getImagen());
        articulo.setEstado(articuloParam.getEstado());
        articulo.setCategoria(categoria);

        articuloService.save(articulo);

        return  "redirect:/admin/articulos";
    }

    @GetMapping("/admin/articulo/eliminar/{id}")
    public String eliminarArticulo(@PathVariable("id") int id) {
        articuloService.deleteByIdArticulo(id);
        return  "redirect:/admin/articulos";
    }
}
