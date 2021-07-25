package com.untels.service;

import java.util.List;

import com.untels.entity.Ingreso;
import com.untels.repository.IngresoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoService {

    @Autowired
    IngresoRepository ingresoRepository;

    public List<Ingreso> findAll() {
        return ingresoRepository.findAll();
    }

    public Ingreso findByIdIngreso(long id) {
        return ingresoRepository.findByIdIngreso(id);
    }

    public void save(Ingreso ingreso) {
        ingresoRepository.save(ingreso);
    }

    public void deleteByIdIngreso(long id) {
        ingresoRepository.deleteById(id);
    }

    public boolean existsByIdIngreso(long id) {
        return ingresoRepository.existsById(id);
    }

}