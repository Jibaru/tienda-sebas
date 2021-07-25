package com.untels.service;

import java.util.List;

import com.untels.entity.DetalleIngreso;
import com.untels.repository.DetalleIngresoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleIngresoService {

    @Autowired
    DetalleIngresoRepository detalleIngresoRepository;

    public List<DetalleIngreso> findAll() {
        return detalleIngresoRepository.findAll();
    }

    public void save(DetalleIngreso detalleVenta) {
        detalleIngresoRepository.save(detalleVenta);
    }

    public void deleteByIdDetalleIngreso(long idDetalleIngreso) {
        detalleIngresoRepository.deleteById(idDetalleIngreso);
    }

    public boolean existePorIdDetalleIngreso(long idDetalleIngreso) {
        return detalleIngresoRepository.existsById(idDetalleIngreso);
    }

    public DetalleIngreso findByIdDetalleIngreso(long idDetalleIngreso) {
        return detalleIngresoRepository.getOne(idDetalleIngreso);
    }
}