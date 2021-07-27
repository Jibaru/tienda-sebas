package com.untels.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_detalle_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDetalleVenta;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio", nullable = false)
    private float precio;

    @Column(name = "descuento")
    private float descuento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    private Venta venta;

    @ManyToOne()
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo")
    private Articulo articulo;

    public DetalleVenta() {
    }

    public DetalleVenta(int cantidad, float precio, float descuento, Venta venta, Articulo articulo) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.venta = venta;
        this.articulo = articulo;
    }

    public long getIdDetalleVenta() {
        return this.idDetalleVenta;
    }

    public void setIdDetalleVenta(long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
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

    public float getDescuento() {
        return this.descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Venta getVenta() {
        return this.venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Articulo getArticulo() {
        return this.articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

}
