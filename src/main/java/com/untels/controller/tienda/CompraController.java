package com.untels.controller.tienda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.untels.entity.Articulo;
import com.untels.security.supervisor.Supervisor;
import com.untels.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompraController {

    @Autowired
    VentaService ventaService;

    @Autowired
    HttpSession session;

    @GetMapping("/compras")
    public String compras(Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/";
        }

        // TODO: Filtrar por id usuario
        model.addAttribute("compras", ventaService.findAll());

        return "tienda/compras/lista-compras";
    }

    @GetMapping("/compras/{id}")
    public String compra(@PathVariable("id") long id, Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/";
        }

        // TODO: Filtrar por id usuario e id de venta
        model.addAttribute("compra", ventaService.findByIdVenta(id));

        return "tienda/compras/ver-compra";
    }

    // Accede a los datos del carrito mediante la sesion
    @GetMapping("/carrito")
    public String carrito(Model model) {
        @SuppressWarnings("unchecked")
        List<Articulo> articulosCarrito = (ArrayList<Articulo>) session.getAttribute("articulosCarrito");

        if (articulosCarrito == null) {
            articulosCarrito = new ArrayList<>();
            session.setAttribute("articulosCarrito", articulosCarrito);
        }

        return "tienda/compras/ver-carrito";
    }
}
