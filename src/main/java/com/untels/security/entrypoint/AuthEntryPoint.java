package com.untels.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * Ocurre ante un error de autenticación, roles, falla (url no registrada)
     */
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
            throws IOException, ServletException {

        // String uri = req.getRequestURI();
        e.printStackTrace();
        res.getOutputStream().print(e.getMessage());
        /*
         * if (uri.contains("admin")) { res.sendRedirect("/admin"); } else {
         * res.sendRedirect("/"); }
         */
    }

}
