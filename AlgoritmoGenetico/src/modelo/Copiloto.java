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
@Table(name = "copiloto", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Copiloto.findAll", query = "SELECT c FROM Copiloto c"),
    @NamedQuery(name = "Copiloto.findByIdCopiloto", query = "SELECT c FROM Copiloto c WHERE c.idCopiloto = :idCopiloto"),
    @NamedQuery(name = "Copiloto.findByFechaRegistro", query = "SELECT c FROM Copiloto c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Copiloto.findByEstado", query = "SELECT c FROM Copiloto c WHERE c.estado = :estado")})
public class Copiloto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCopiloto")
    private Integer idCopiloto;
    @Basic(optional = false)
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersonaNatural")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona idPersona;
    @JoinColumn(name = "idTurno", referencedColumnName = "idTurno")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Turno idTurno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCopiloto", fetch = FetchType.LAZY)
    private List<Ruta> rutaList;

    public Copiloto() {
    }

    public Copiloto(Integer idCopiloto) {
        this.idCopiloto = idCopiloto;
    }

    public Copiloto(Integer idCopiloto, Date fechaRegistro, String estado) {
        this.idCopiloto = idCopiloto;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public Integer getIdCopiloto() {
        return idCopiloto;
    }

    public void setIdCopiloto(Integer idCopiloto) {
        this.idCopiloto = idCopiloto;
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

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Turno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Turno idTurno) {
        this.idTurno = idTurno;
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
        hash += (idCopiloto != null ? idCopiloto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Copiloto)) {
            return false;
        }
        Copiloto other = (Copiloto) object;
        if ((this.idCopiloto == null && other.idCopiloto != null) || (this.idCopiloto != null && !this.idCopiloto.equals(other.idCopiloto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Copiloto[ idCopiloto=" + idCopiloto + " ]";
    }
    
}
