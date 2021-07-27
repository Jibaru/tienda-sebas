package com.untels.controller.admin;

import javax.servlet.http.HttpSession;

import com.untels.dto.auth.EmailClaveDTO;
import com.untels.entity.Usuario;
import com.untels.enums.Rol;
import com.untels.security.supervisor.Supervisor;
import com.untels.service.PersonaService;
import com.untels.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    HttpSession session;

    @Autowired
    PersonaService personaService;

    @GetMapping("/admin")
    public String principal() {
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin/inicio-sesion";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin/inicio-sesion";
        }

        return "admin/index";
    }

    @GetMapping("/admin/inicio-sesion")
    public String inicioSesion(Model model) {
        model.addAttribute("emailClave", new EmailClaveDTO());
        return "admin/auth/inicio-sesion";
    }

    @PostMapping("/admin/iniciar-sesion")
    public String iniciarSesion(@ModelAttribute("emailClave") EmailClaveDTO emailClaveDTO) {
        if (!usuarioService.credencialesCorrectos(emailClaveDTO)) {
            session.setAttribute("mensaje", "Email o Contraseña incorrectos");
            return "redirect:/admin/inicio-sesion";
        }

        Usuario usuario = usuarioService.findByEmail(emailClaveDTO.getEmail()).get();

        if(usuario.getRol()!=Rol.ADMIN){
            session.setAttribute("mensaje", "Credenciales incorrectas");
            return "redirect:/admin/inicio-sesion";
        }

        session.setMaxInactiveInterval(10 * 60);
        session.removeAttribute("mensaje");
        session.setAttribute("usuario", usuario);

        return "redirect:/admin";
    }

    @GetMapping("/admin/cerrar-sesion")
    public String cerrarSesion() {
        session.removeAttribute("usuario");
        return "redirect:/admin";
    }
}
