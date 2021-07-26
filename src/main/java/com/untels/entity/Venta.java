package com.untels.entity;

import java.io.Serializable;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.untels.enums.EstadoVenta;
import com.untels.enums.TipoComprobante;

@Entity
@Table(name = "venta")
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVenta;

    @Column(name = "tipo_comprobante", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;

    @Column(name = "serie_comprobante", nullable = false)
    private String serieComprobante;

    @Column(name = "num_comprobante", nullable = false)
    private String numComprobante;

    @Column(name = "fecha", nullable = false)
    private GregorianCalendar fecha;

    @Column(name = "impuesto")
    private float impuesto;

    @Column(name = "total", nullable = false)
    private float total;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_persona")
    @JsonIgnore
    private Persona cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_gerente", referencedColumnName = "id_persona")
    @JsonIgnore
    private Persona gerente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<DetalleVenta> detalleVentas = new HashSet<>();

    public Venta() {
    }

    public Venta(TipoComprobante tipoComprobante, String serieComprobante, String numComprobante,
            GregorianCalendar fecha, float impuesto, float total, EstadoVenta estado, Persona cliente, Persona gerente,
            Set<DetalleVenta> detalleVentas) {
        this.tipoComprobante = tipoComprobante;
        this.serieComprobante = serieComprobante;
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.gerente = gerente;
        this.detalleVentas = detalleVentas;
    }

    public long getIdVenta() {
        return this.idVenta;
    }

    public void setIdventa(long idVenta) {
        this.idVenta = idVenta;
    }

    public TipoComprobante getTipoComprobante() {
        return this.tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerieComprobante() {
        return this.serieComprobante;
    }

    public void setSerieComprobante(String serieComprobante) {
        this.serieComprobante = serieComprobante;
    }

    public String getNumComprobante() {
        return this.numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public GregorianCalendar getFecha() {
        return this.fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public float getImpuesto() {
        return this.impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public EstadoVenta getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public Persona getCliente() {
        return this.cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Persona getGerente() {
        return this.gerente;
    }

    public void setGerente(Persona gerente) {
        this.gerente = gerente;
    }

    public Set<DetalleVenta> getDetalleVentas() {
        return this.detalleVentas;
    }

    public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

}
