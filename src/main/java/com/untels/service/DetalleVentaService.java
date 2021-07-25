package com.untels.service;

import java.util.List;

import com.untels.entity.DetalleVenta;
import com.untels.repository.DetalleVentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaService {

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    public void save(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }

    public void deleteByIdArticulo(long idDetalleVenta) {
        detalleVentaRepository.deleteById(idDetalleVenta);
    }

    public boolean existePorIdArticulo(long idDetalleVenta) {
        return detalleVentaRepository.existsById(idDetalleVenta);
    }

    public DetalleVenta findByIdArticulo(long idDetalleVenta) {
        return detalleVentaRepository.getOne(idDetalleVenta);
    }
}