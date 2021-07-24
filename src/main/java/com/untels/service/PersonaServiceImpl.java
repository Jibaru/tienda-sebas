package com.untels.service;

import java.util.List;

import com.untels.entity.Persona;
import com.untels.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
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