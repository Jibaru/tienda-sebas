package com.untels.repository;

import com.untels.entity.Articulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    public Articulo findByIdArticulo(long idArticulo);

    public void deleteByIdArticulo(long idArticulo);

    public boolean existsByIdArticulo(long idArticulo);
}
