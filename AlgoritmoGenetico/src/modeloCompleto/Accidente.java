/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloCompleto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "accidente", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accidente.findAll", query = "SELECT a FROM Accidente a"),
    @NamedQuery(name = "Accidente.findByIdAccidente", query = "SELECT a FROM Accidente a WHERE a.idAccidente = :idAccidente"),
    @NamedQuery(name = "Accidente.findByFecha", query = "SELECT a FROM Accidente a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Accidente.findByHora", query = "SELECT a FROM Accidente a WHERE a.hora = :hora"),
    @NamedQuery(name = "Accidente.findByEstado", query = "SELECT a FROM Accidente a WHERE a.estado = :estado"),
    @NamedQuery(name = "Accidente.findByObservacion", query = "SELECT a FROM Accidente a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "Accidente.findBySolucion", query = "SELECT a FROM Accidente a WHERE a.solucion = :solucion")})
public class Accidente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccidente")
    private Integer idAccidente;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @Column(name = "Observacion")
    private String observacion;
    @Column(name = "Solucion")
    private String solucion;
    @OneToMany(mappedBy = "idAccidente", fetch = FetchType.LAZY)
    private List<Ruta> rutaList;
    @JoinColumn(name = "idNodo", referencedColumnName = "idNodo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nodo idNodo;

    public Accidente() {
    }

    public Accidente(Integer idAccidente) {
        this.idAccidente = idAccidente;
    }

    public Accidente(Integer idAccidente, Date fecha, Date hora, String estado) {
        this.idAccidente = idAccidente;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Integer getIdAccidente() {
        return idAccidente;
    }

    public void setIdAccidente(Integer idAccidente) {
        this.idAccidente = idAccidente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @XmlTransient
    public List<Ruta> getRutaList() {
        return rutaList;
    }

    public void setRutaList(List<Ruta> rutaList) {
        this.rutaList = rutaList;
    }

    public Nodo getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(Nodo idNodo) {
        this.idNodo = idNodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccidente != null ? idAccidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accidente)) {
            return false;
        }
        Accidente other = (Accidente) object;
        if ((this.idAccidente == null && other.idAccidente != null) || (this.idAccidente != null && !this.idAccidente.equals(other.idAccidente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Accidente[ idAccidente=" + idAccidente + " ]";
    }
    
}
