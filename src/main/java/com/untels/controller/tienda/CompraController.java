package com.untels.controller.tienda;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.untels.dto.compras.CarritoArticuloDTO;
import com.untels.dto.compras.CarritoCompraDTO;
import com.untels.entity.Articulo;
import com.untels.entity.DetalleVenta;
import com.untels.entity.Persona;
import com.untels.entity.Usuario;
import com.untels.entity.Venta;
import com.untels.enums.EstadoVenta;
import com.untels.enums.TipoComprobante;
import com.untels.security.supervisor.Supervisor;
import com.untels.service.ArticuloService;
import com.untels.service.CategoriaService;
import com.untels.service.DetalleVentaService;
import com.untels.service.PersonaService;
import com.untels.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CompraController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    VentaService ventaService;

    @Autowired
    DetalleVentaService detalleVentaService;

    @Autowired
    PersonaService personaService;

    @Autowired
    HttpSession session;

    @GetMapping("/compras")
    public String compras(Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/inicio-sesion";
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Venta> ventas = ventaService.findAll();
        List<Venta> filtro = new ArrayList<>();
        for (Venta v : ventas) {
            if (v.getCliente().getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
                filtro.add(v);
            }
        }

        model.addAttribute("listaCompras", filtro);
        model.addAttribute("listaCategorias", categoriaService.findAll());

        return "tienda/compras/lista-compras";
    }

    @PostMapping("/compras/cancelar/{id}")
    public String compra(@PathVariable("id") long id, Model model) {

        if (!ventaService.existsByIdVenta(id)) {
            return "redirect:/compras";
        }

        Venta venta = ventaService.findByIdVenta(id);
        venta.setEstado(EstadoVenta.ANULADO);
        ventaService.save(venta);

        return "redirect:/compras";
    }

    // Accede a los datos del carrito mediante la sesion
    @GetMapping("/carrito")
    public String carrito(Model model) {
        @SuppressWarnings("unchecked")
        List<CarritoArticuloDTO> carrito = (ArrayList<CarritoArticuloDTO>) session.getAttribute("carrito");

        if (carrito == null) {
            return "redirect:/";
        }

        model.addAttribute("listaCategorias", categoriaService.findAll());

        return "tienda/compras/ver-carrito";
    }

    @PostMapping("/carrito/agregar")
    public String agregarCarrito(@ModelAttribute("datos") CarritoCompraDTO.Dato datos, Model model) {

        Articulo articulo = articuloService.findByIdArticulo(datos.getIdArticulo());
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new ArrayList<CarritoArticuloDTO>());
        }
        @SuppressWarnings("unchecked")
        List<CarritoArticuloDTO> carrito = (List<CarritoArticuloDTO>) session.getAttribute("carrito");

        CarritoArticuloDTO dto = new CarritoArticuloDTO();
        dto.setIdArticulo(articulo.getIdArticulo());
        dto.setNombre(articulo.getNombre());
        dto.setDescripcion(articulo.getDescripcion());
        dto.setPrecioVenta(articulo.getPrecioVenta());
        dto.setCantidad(datos.getCantidad());
        carrito.add(dto);

        session.setAttribute("carrito", carrito);

        return "redirect:/";
    }

    @PostMapping("/carrito/remover/{id}")
    public String removerCarrito(@PathVariable("id") long id, Model model) {

        if (session.getAttribute("carrito") == null) {
            return "redirect:/carrito";
        }
        @SuppressWarnings("unchecked")
        List<CarritoArticuloDTO> carrito = (List<CarritoArticuloDTO>) session.getAttribute("carrito");

        for (CarritoArticuloDTO d : carrito) {
            if (d.getIdArticulo() == id) {
                carrito.remove(d);
                session.setAttribute("carrito", carrito);
                return "redirect:/carrito";
            }
        }

        return "redirect:/carrito";
    }

    @PostMapping("/realizar-compra")
    public String realizarCompra(@RequestBody CarritoCompraDTO carritoCompra, Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/inicio-sesion";
        }

        Usuario cliente = (Usuario) session.getAttribute("usuario");
        Persona personaCliente = personaService.findByIdPersona(cliente.getPersona().getIdPersona());

        System.out.println("carrito:");
        System.out.println(carritoCompra.getDatos().get(0).getCantidad());

        Venta venta = new Venta();

        venta.setTipoComprobante(TipoComprobante.BOLETA);
        venta.setSerieComprobante("0001");
        venta.setNumComprobante(Integer.toString((int) new Date().getTime()));
        venta.setFecha(new GregorianCalendar());
        venta.setImpuesto(1);
        venta.setTotal(0);
        venta.setEstado(EstadoVenta.PROCESO);
        venta.setCliente(personaCliente);
        // venta.setGerente(gerente);
        ventaService.save(venta);
        float totalVenta = 0.0f;

        for (CarritoCompraDTO.Dato dato : carritoCompra.getDatos()) {
            DetalleVenta detalleVenta = new DetalleVenta();
            Articulo articulo = articuloService.findByIdArticulo(dato.getIdArticulo());
            float totalArticulo = articulo.getPrecioVenta() * dato.getCantidad();
            totalVenta += totalArticulo;
            detalleVenta.setCantidad(dato.getCantidad());
            detalleVenta.setArticulo(articulo);
            detalleVenta.setDescuento(0.0f);
            detalleVenta.setPrecio(totalArticulo);
            detalleVenta.setVenta(venta);

            detalleVentaService.save(detalleVenta);
        }

        venta.setTotal(totalVenta);
        ventaService.save(venta);

        return "redirect:/compras";
    }

}
