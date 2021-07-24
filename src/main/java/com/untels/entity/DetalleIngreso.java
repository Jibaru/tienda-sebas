package com.untels.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_ingreso")
public class DetalleIngreso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_detalle_ingreso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDetalleIngreso;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio", nullable = false)
    private float precio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo")
    @JsonIgnore
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ingreso", referencedColumnName = "id_ingreso")
    @JsonIgnore
    private Ingreso ingreso;

    public DetalleIngreso(int cantidad, float precio, Articulo articulo, Ingreso ingreso) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.articulo = articulo;
        this.ingreso = ingreso;
    }

    public DetalleIngreso() {
    }

    public long getIdDetalleIngreso() {
        return this.idDetalleIngreso;
    }

    public void setIdDetalleIngreso(long idDetalleIngreso) {
        this.idDetalleIngreso = idDetalleIngreso;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Articulo getArticulo() {
        return this.articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Ingreso getIngreso() {
        return this.ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

}
