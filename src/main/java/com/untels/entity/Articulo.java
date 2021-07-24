package com.untels.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_articulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idArticulo;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio_venta", nullable = false)
    private float precioVenta;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @JsonIgnore
    private Categoria categoria;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private Set<DetalleIngreso> detalleIngresos = new HashSet<>();

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private Set<DetalleVenta> detalleVentas = new HashSet<>();

    public Articulo(String codigo, String nombre, float precioVenta, int stock, String descripcion, String imagen,
            boolean estado, Categoria categoria, Set<DetalleIngreso> detalleIngresos, Set<DetalleVenta> detalleVentas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.estado = estado;
        this.categoria = categoria;
        this.detalleIngresos = detalleIngresos;
        this.detalleVentas = detalleVentas;
    }

    public Articulo() {
    }

    public long getIdArticulo() {
        return this.idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<DetalleIngreso> getDetalleIngresos() {
        return this.detalleIngresos;
    }

    public void setDetalleIngresos(Set<DetalleIngreso> detalleIngresos) {
        this.detalleIngresos = detalleIngresos;
    }

    public Set<DetalleVenta> getDetalleVentas() {
        return this.detalleVentas;
    }

    public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

}
