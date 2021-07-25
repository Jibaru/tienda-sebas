package com.untels.service;

import java.util.List;

import com.untels.entity.Articulo;
import com.untels.repository.ArticuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    public List<Articulo> findAll() {
        return articuloRepository.findAll();
    }

    public void save(Articulo articulo) {
        articuloRepository.save(articulo);
    }

    public void deleteByIdArticulo(long idArticulo) {
        articuloRepository.deleteById(idArticulo);
    }

    public boolean existePorIdArticulo(long idArticulo) {
        return articuloRepository.existsById(idArticulo);
    }

    public Articulo findByIdArticulo(long idArticulo) {
        return articuloRepository.getOne(idArticulo);
    }
}
