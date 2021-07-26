package com.untels.dto.compras;

import java.util.List;

public class CarritoCompraDTO {
    List<Dato> datos;

    public static class Dato {
        private Long idArticulo;
        private Integer cantidad;

        public Dato() {
        }

        public Long getIdArticulo() {
            return idArticulo;
        }

        public void setIdArticulo(long idArticulo) {
            this.idArticulo = idArticulo;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }

    public CarritoCompraDTO() {
    }

    public List<Dato> getDatos() {
        return this.datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }

}