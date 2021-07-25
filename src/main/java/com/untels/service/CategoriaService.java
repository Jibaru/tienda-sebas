package com.untels.service;

import java.util.List;

import com.untels.entity.Categoria;
import com.untels.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findByIdCategoria(long id) {
        return categoriaRepository.getOne(id);
    }

    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void deleteById(long id) {
        categoriaRepository.deleteById(id);
    }

    public boolean existsByIdCategoria(long idCategoria) {
        return categoriaRepository.existsById(idCategoria);
    }

}