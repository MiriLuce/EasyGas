/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
@Table(name = "mantenimiento", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m"),
    @NamedQuery(name = "Mantenimiento.findByIdMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.idMantenimiento = :idMantenimiento"),
    @NamedQuery(name = "Mantenimiento.findByFecha", query = "SELECT m FROM Mantenimiento m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mantenimiento.findByHora", query = "SELECT m FROM Mantenimiento m WHERE m.hora = :hora"),
    @NamedQuery(name = "Mantenimiento.findByEstado", query = "SELECT m FROM Mantenimiento m WHERE m.estado = :estado"),
    @NamedQuery(name = "Mantenimiento.findByIdTipoMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.idTipoMantenimiento = :idTipoMantenimiento"),
    @NamedQuery(name = "Mantenimiento.findByCantTurnos", query = "SELECT m FROM Mantenimiento m WHERE m.cantTurnos = :cantTurnos")})
public class Mantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMantenimiento")
    private Integer idMantenimiento;
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
    @Basic(optional = false)
    @Column(name = "idTipoMantenimiento")
    private int idTipoMantenimiento;
    @Basic(optional = false)
    @Column(name = "CantTurnos")
    private int cantTurnos;
    @OneToMany(mappedBy = "idMantenimiento", fetch = FetchType.LAZY)
    private List<Camion> camionList;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Mantenimiento(Integer idMantenimiento, Date fecha, Date hora, String estado, int idTipoMantenimiento, int cantTurnos) {
        this.idMantenimiento = idMantenimiento;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idTipoMantenimiento = idTipoMantenimiento;
        this.cantTurnos = cantTurnos;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
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

    public int getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(int idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public int getCantTurnos() {
        return cantTurnos;
    }

    public void setCantTurnos(int cantTurnos) {
        this.cantTurnos = cantTurnos;
    }

    @XmlTransient
    public List<Camion> getCamionList() {
        return camionList;
    }

    public void setCamionList(List<Camion> camionList) {
        this.camionList = camionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimiento != null ? idMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.idMantenimiento == null && other.idMantenimiento != null) || (this.idMantenimiento != null && !this.idMantenimiento.equals(other.idMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Mantenimiento[ idMantenimiento=" + idMantenimiento + " ]";
    }
    
}
