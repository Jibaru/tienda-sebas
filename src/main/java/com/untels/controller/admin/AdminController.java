package com.untels.controller.admin;

import com.untels.dto.auth.EmailClaveDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String principal() {
        return "admin/index";
    }

    @GetMapping("/admin/inicio-sesion")
    public String inicioSesion(Model model) {
        model.addAttribute("emailClave", new EmailClaveDTO());
        return "admin/auth/inicio-sesion";
    }

}
