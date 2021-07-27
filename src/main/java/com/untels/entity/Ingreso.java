package com.untels.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.untels.enums.TipoComprobante;

@Entity
@Table(name = "ingreso")
public class Ingreso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_ingreso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIngreso;

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
    private boolean estado;

    @ManyToOne()
    @JoinColumn(name = "id_gerente", referencedColumnName = "id_persona")
    @JsonIgnore
    private Persona gerente;

    @ManyToOne()
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_persona")
    @JsonIgnore
    private Persona proveedor;

    @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL)
    private Set<DetalleIngreso> detalleIngresos;

    public Ingreso() {
    }

    public Ingreso(TipoComprobante tipoComprobante, String serieComprobante, String numComprobante,
            GregorianCalendar fecha, float impuesto, float total, boolean estado, Persona gerente, Persona proveedor,
            Set<DetalleIngreso> detalleIngresos) {
        this.tipoComprobante = tipoComprobante;
        this.serieComprobante = serieComprobante;
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.total = total;
        this.estado = estado;
        this.gerente = gerente;
        this.proveedor = proveedor;
        this.detalleIngresos = detalleIngresos;
    }

    public long getIdIngreso() {
        return this.idIngreso;
    }

    public void setIdIngreso(long idIngreso) {
        this.idIngreso = idIngreso;
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

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Persona getGerente() {
        return this.gerente;
    }

    public void setGerente(Persona gerente) {
        this.gerente = gerente;
    }

    public Persona getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Persona proveedor) {
        this.proveedor = proveedor;
    }

    public Set<DetalleIngreso> getDetalleIngresos() {
        return this.detalleIngresos;
    }

    public void setDetalleIngresos(Set<DetalleIngreso> detalleIngresos) {
        this.detalleIngresos = detalleIngresos;
    }

}
