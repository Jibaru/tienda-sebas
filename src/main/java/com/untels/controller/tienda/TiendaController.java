package com.untels.controller.tienda;

import javax.servlet.http.HttpSession;

import com.untels.dto.auth.EmailClaveDTO;
import com.untels.dto.auth.UsuarioCompletoDTO;
import com.untels.entity.Usuario;
import com.untels.service.ArticuloService;
import com.untels.service.CategoriaService;
import com.untels.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class TiendaController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ArticuloService articuloService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String principal(Model model) {
        model.addAttribute("listaCategorias", categoriaService.findAll());
        model.addAttribute("listaArticulos", articuloService.findAll());
        return "tienda/index";
    }

    @GetMapping("/inicio-sesion")
    public String inicioSesion(Model model) {
        model.addAttribute("emailClave", new EmailClaveDTO());
        return "tienda/auth/inicio-sesion";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuarioCompleto", new UsuarioCompletoDTO());
        return "tienda/auth/registro";
    }

    @PostMapping("/iniciar-sesion")
    public String iniciarSesion(@ModelAttribute("emailClave") EmailClaveDTO emailClaveDTO) {
        if (!usuarioService.credencialesCorrectos(emailClaveDTO)) {
            session.setAttribute("mensaje", "Email o Contraseña incorrectos");
            return "redirect:/inicio-sesion";
        }

        Usuario usuario = usuarioService.findByEmail(emailClaveDTO.getEmail()).get();
        session.setMaxInactiveInterval(10 * 60);
        session.removeAttribute("mensaje");
        session.setAttribute("usuario", usuario);

        return "redirect:/";
    }

    @PostMapping("/registrarse")
    public String registrarse(@ModelAttribute("usuarioCompletoDTO") UsuarioCompletoDTO usuarioCompletoDTO) {
        usuarioService.saveUsuarioCliente(usuarioCompletoDTO);
        return "redirect:/inicio-sesion";
    }

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion() {
        session.removeAttribute("usuario");
        return "redirect:/";
    }
}
