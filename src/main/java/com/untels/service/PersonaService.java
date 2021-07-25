package com.untels.service;

import java.util.List;

import com.untels.entity.Persona;
import com.untels.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public void save(Persona persona) {
        personaRepository.save(persona);
    }

    public void deleteByIdPersona(long idPersona) {
        personaRepository.deleteById(idPersona);
    }

    public boolean existePorIdPersona(long idPersona) {
        return personaRepository.existsById(idPersona);
    }

    public Persona findByIdPersona(long idPersona) {
        return personaRepository.getOne(idPersona);
    }

}

/*
 * package com.untels.service;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * import com.untels.entity.Empleado;
 * 
 * public interface EmpleadoService { List<Empleado> getAllEmpleados();
 * 
 * void grabarEmpleado(Empleado empleado);
 * 
 * Optional<Empleado> getEmpleadoById(long id);
 * 
 * void eliminarEmpleadoById(long id); }
 */

/**
 * package com.untels.service;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * import com.untels.entity.Empleado; import
 * com.untels.repository.EmpleadoRepository;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * @Service public class EmpleadoServiceImpl implements EmpleadoService {
 * 
 * @Autowired EmpleadoRepository empleadoRepository;
 * 
 * @Override public List<Empleado> getAllEmpleados() { return
 *           empleadoRepository.findAll(); }
 * 
 * @Override public void grabarEmpleado(Empleado empleado) {
 *           empleadoRepository.save(empleado); }
 * 
 * @Override public Optional<Empleado> getEmpleadoById(long id) { return
 *           empleadoRepository.findById(id); }
 * 
 * @Override public void eliminarEmpleadoById(long id) {
 *           empleadoRepository.deleteById(id);
 * 
 *           }
 * 
 *           }
 * 
 */