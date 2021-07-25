package com.untels.repository;

import com.untels.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByIdCategoria(long idCategoria);

    public void deleteByIdCategoria(long idCategoria);

    public boolean existsByIdCategoria(long idCategoria);
}