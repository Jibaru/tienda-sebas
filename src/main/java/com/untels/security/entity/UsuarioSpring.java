package com.untels.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.untels.entity.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioSpring implements UserDetails {
    private String email;
    private String clave;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioSpring(String email, String clave, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.clave = clave;
        this.authorities = authorities;
    }

    public static UsuarioSpring build(Usuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().name()));

        return new UsuarioSpring(usuario.getEmail(), usuario.getClave(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
