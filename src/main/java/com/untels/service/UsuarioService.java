package com.untels.service;

import java.util.List;
import java.util.Optional;

import com.untels.dto.auth.UsuarioCompletoDTO;
import com.untels.entity.Persona;
import com.untels.entity.Usuario;
import com.untels.enums.Rol;
import com.untels.enums.TipoPersona;
import com.untels.repository.PersonaRepository;
import com.untels.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void deleteByIdUsuario(long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public boolean existePorIdUsuario(long idUsuario) {
        return usuarioRepository.existsById(idUsuario);
    }

    public Usuario findByIdUsuario(long idUsuario) {
        return usuarioRepository.getOne(idUsuario);
    }

    public Usuario saveUsuarioCliente(UsuarioCompletoDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setClave(passwordEncoder.encode(dto.getClave()));
        usuario.setEstado(true);
        usuario.setRol(Rol.NORMAL);
        usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setApellidoPaterno(dto.getApellidoPaterno());
        persona.setApellidoMaterno(dto.getApellidoMaterno());
        persona.setTipoPersona(TipoPersona.CLIENTE);
        persona.setUsuario(usuario);

        personaRepository.save(persona);
        usuario.setPersona(persona);

        return usuario;
    }
}
