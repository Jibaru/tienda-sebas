package com.untels.repository;

import com.untels.entity.Venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    public Venta findByIdVenta(long idVenta);

    public void deleteByIdVenta(long idVenta);

    public boolean existsByIdVenta(long idVenta);
}