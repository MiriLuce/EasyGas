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
import javax.persistence.CascadeType;
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
@Table(name = "camion", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c"),
    @NamedQuery(name = "Camion.findByIdCamion", query = "SELECT c FROM Camion c WHERE c.idCamion = :idCamion"),
    @NamedQuery(name = "Camion.findByPlaca", query = "SELECT c FROM Camion c WHERE c.placa = :placa"),
    @NamedQuery(name = "Camion.findByFechaRegistro", query = "SELECT c FROM Camion c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Camion.findByEstado", query = "SELECT c FROM Camion c WHERE c.estado = :estado")})
public class Camion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCamion")
    private Integer idCamion;
    @Basic(optional = false)
    @Column(name = "Placa")
    private String placa;
    @Basic(optional = false)
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "idMantenimiento", referencedColumnName = "idMantenimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mantenimiento idMantenimiento;
    @JoinColumn(name = "idTipoCamion", referencedColumnName = "idTipoCamion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCamion idTipoCamion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCamion", fetch = FetchType.LAZY)
    private List<Ruta> rutaList;

    public Camion() {
    }

    public Camion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public Camion(Integer idCamion, String placa, Date fechaRegistro, String estado) {
        this.idCamion = idCamion;
        this.placa = placa;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Mantenimiento getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Mantenimiento idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public TipoCamion getIdTipoCamion() {
        return idTipoCamion;
    }

    public void setIdTipoCamion(TipoCamion idTipoCamion) {
        this.idTipoCamion = idTipoCamion;
    }

    @XmlTransient
    public List<Ruta> getRutaList() {
        return rutaList;
    }

    public void setRutaList(List<Ruta> rutaList) {
        this.rutaList = rutaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamion != null ? idCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.idCamion == null && other.idCamion != null) || (this.idCamion != null && !this.idCamion.equals(other.idCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Camion[ idCamion=" + idCamion + " ]";
    }
    
}
