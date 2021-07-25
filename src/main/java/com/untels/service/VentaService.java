package com.untels.service;

import java.util.List;

import com.untels.entity.Venta;
import com.untels.repository.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findByIdVenta(long id) {
        return ventaRepository.getOne(id);
    }

    public void save(Venta venta) {
        ventaRepository.save(venta);
    }

    public void deleteByIdVenta(long idVenta) {
        ventaRepository.deleteById(idVenta);
    }

    public boolean existsByIdVenta(long idVenta) {
        return ventaRepository.existsById(idVenta);
    }

}
