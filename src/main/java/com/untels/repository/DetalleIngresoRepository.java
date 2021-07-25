package com.untels.repository;

import com.untels.entity.DetalleIngreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleIngresoRepository extends JpaRepository<DetalleIngreso, Long> {
    public DetalleIngreso findByIdDetalleIngreso(long idDetalleIngreso);
}