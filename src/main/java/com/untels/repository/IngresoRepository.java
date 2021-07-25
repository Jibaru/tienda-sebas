package com.untels.repository;

import com.untels.entity.Ingreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    public Ingreso findByIdIngreso(long idIngreso);
}
