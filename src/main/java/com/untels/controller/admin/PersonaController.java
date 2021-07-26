package com.untels.controller.admin;

import com.untels.service.PersonaService;
import com.untels.service.UsuarioService;

import javax.servlet.http.HttpSession;

import com.untels.dto.personas.PersonaUsuarioDTO;
import com.untels.entity.Persona;
import com.untels.entity.Usuario;
import com.untels.enums.TipoDocumento;
import com.untels.enums.Rol;
import com.untels.enums.TipoPersona;
import com.untels.security.supervisor.Supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

    @GetMapping("/admin/personas")
    public String gestionPersonas(Model model) {
        
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
      
        model.addAttribute("listaPersonas", personaService.findAll());
        return "admin/personas/lista-personas";
    }

    @GetMapping("/admin/personas/nuevo")
    public String crearPersona(Model model) {
       
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        model.addAttribute("persona", new PersonaUsuarioDTO());
        return "admin/personas/form-persona";
    }

    @PostMapping("/admin/personas/grabar")
    public String grabarPersona(@ModelAttribute("persona") PersonaUsuarioDTO personaDTO) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        Persona persona = new Persona();
        Usuario usuario = new Usuario();

        persona.setNombre(personaDTO.getNombre());
        persona.setApellidoPaterno(personaDTO.getApellidoPaterno());
        persona.setApellidoMaterno(personaDTO.getApellidoMaterno());
        persona.setTipoPersona(TipoPersona.fromString(personaDTO.getTipoPersona()));
        persona.setTipoDocumento(TipoDocumento.fromString(personaDTO.getTipoDocumento()));
        persona.setNumDocumento(personaDTO.getNumDocumento());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setTelefono(personaDTO.getTelefono());
        usuario.setEmail(personaDTO.getEmail());
        usuario.setRol(Rol.fromString(personaDTO.getRol()));
        usuario.setEstado(personaDTO.getEstado() == "activo");
        usuario.setClave(passwordEncoder.encode(personaDTO.getClave()));

        usuarioService.save(usuario);
        persona.setUsuario(usuario);
        personaService.save(persona);

        return  "redirect:/admin/personas";
    }

    @GetMapping("/admin/personas/{id}")
    public String editarPersona(@PathVariable("id") int id, Model model) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        if (!personaService.existePorIdPersona(id)) {
            // TODO: Cambiar a pagina de error
            return "pagina-404";
        }

        Persona persona = personaService.findByIdPersona(id);
        PersonaUsuarioDTO personaDTO = new PersonaUsuarioDTO();

        personaDTO.setIdUsuario(persona.getUsuario().getIdUsuario());
        personaDTO.setIdPersona(persona.getIdPersona());
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setApellidoPaterno(persona.getApellidoPaterno());
        personaDTO.setApellidoMaterno(persona.getApellidoMaterno());
        personaDTO.setTipoPersona(persona.getTipoPersona().name());
        personaDTO.setTipoDocumento(persona.getTipoDocumento().name());
        personaDTO.setNumDocumento(persona.getNumDocumento());
        personaDTO.setDireccion(persona.getDireccion());
        personaDTO.setTelefono(persona.getTelefono());
        personaDTO.setEmail(persona.getUsuario().getEmail());
        personaDTO.setRol(persona.getUsuario().getRol().name());
        personaDTO.setEstado(persona.getUsuario().getEstado() ? "activo":"inactivo");

        model.addAttribute("persona", personaDTO);

        return "admin/personas/form-persona";
    }

    @PostMapping("/admin/personas/editar")
    public String editarPersonaForm(@ModelAttribute("persona") PersonaUsuarioDTO personaDTO) {

        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }

        Persona persona = personaService.findByIdPersona(personaDTO.getIdPersona());

        persona.setNombre(personaDTO.getNombre());
        persona.setApellidoPaterno(personaDTO.getApellidoPaterno());
        persona.setApellidoMaterno(personaDTO.getApellidoMaterno());
        persona.setTipoPersona(TipoPersona.fromString(personaDTO.getTipoPersona()));
        persona.setTipoDocumento(TipoDocumento.fromString(personaDTO.getTipoDocumento()));
        persona.setNumDocumento(personaDTO.getNumDocumento());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setTelefono(personaDTO.getTelefono());
        persona.getUsuario().setEmail(personaDTO.getEmail());
        persona.getUsuario().setRol(Rol.fromString(personaDTO.getRol()));
        persona.getUsuario().setEstado(personaDTO.getEstado() == "activo");

        personaService.save(persona);
        usuarioService.save(persona.getUsuario());

        return  "redirect:/admin/personas";
    }

    @GetMapping("/admin/personas/eliminar/{id}")
    public String eliminarPersona(@PathVariable("id") int id) {
       
        if (!Supervisor.haIniciadoSesion(session)) {
            return "redirect:/admin";
        }

        if (!Supervisor.tieneRol(session, Rol.ADMIN)) {
            return "redirect:/admin";
        }
       
        personaService.deleteByIdPersona(id);
        return  "redirect:/admin/personas";
    }

}
