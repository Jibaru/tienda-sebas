package com.untels.security.supervisor;

import javax.servlet.http.HttpSession;

import com.untels.entity.Usuario;
import com.untels.enums.Rol;

public class Supervisor {

    public static boolean haIniciadoSesion(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        return usuario != null;
    }

    public static boolean tieneRol(HttpSession session, Rol rol) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        return usuario.getRol() == rol;
    }
}
