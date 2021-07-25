package com.untels.controller.tienda;

import javax.servlet.http.HttpSession;

import com.untels.dto.auth.EmailClaveDTO;
import com.untels.dto.auth.UsuarioCompletoDTO;
import com.untels.entity.Usuario;
import com.untels.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class TiendaController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String principal() {
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailClaveDTO.getEmail(), emailClaveDTO.getClave()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername()).get();

        session.setAttribute("usuario", usuario);

        return "redirect:/";
    }

    @PostMapping("/registrarse")
    public String registrarse(@ModelAttribute("usuarioCompletoDTO") UsuarioCompletoDTO usuarioCompletoDTO) {
        usuarioService.saveUsuarioCliente(usuarioCompletoDTO);
        return "redirect:/inicio-sesion";
    }
}
