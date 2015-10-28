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
@Table(name = "ruta", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r"),
    @NamedQuery(name = "Ruta.findByIdRuta", query = "SELECT r FROM Ruta r WHERE r.idRuta = :idRuta"),
    @NamedQuery(name = "Ruta.findByFechaEntrega", query = "SELECT r FROM Ruta r WHERE r.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Ruta.findByHoraSalida", query = "SELECT r FROM Ruta r WHERE r.horaSalida = :horaSalida"),
    @NamedQuery(name = "Ruta.findByHoraLlegada", query = "SELECT r FROM Ruta r WHERE r.horaLlegada = :horaLlegada"),
    @NamedQuery(name = "Ruta.findByCantGLP", query = "SELECT r FROM Ruta r WHERE r.cantGLP = :cantGLP"),
    @NamedQuery(name = "Ruta.findByCantDiesel", query = "SELECT r FROM Ruta r WHERE r.cantDiesel = :cantDiesel")})
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRuta")
    private Integer idRuta;
    @Basic(optional = false)
    @Column(name = "FechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @Column(name = "HoraSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSalida;
    @Column(name = "HoraLlegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaLlegada;
    @Basic(optional = false)
    @Column(name = "CantGLP")
    private int cantGLP;
    @Basic(optional = false)
    @Column(name = "CantDiesel")
    private int cantDiesel;
    @JoinColumn(name = "idAccidente", referencedColumnName = "idAccidente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Accidente idAccidente;
    @JoinColumn(name = "idCamion", referencedColumnName = "idCamion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Camion idCamion;
    @JoinColumn(name = "idConductor", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conductor idConductor;
    @JoinColumn(name = "idCopiloto", referencedColumnName = "idCopiloto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Copiloto idCopiloto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRuta", fetch = FetchType.LAZY)
    private List<Arista> aristaList;

    public Ruta() {
    }

    public Ruta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Ruta(Integer idRuta, Date fechaEntrega, Date horaSalida, int cantGLP, int cantDiesel) {
        this.idRuta = idRuta;
        this.fechaEntrega = fechaEntrega;
        this.horaSalida = horaSalida;
        this.cantGLP = cantGLP;
        this.cantDiesel = cantDiesel;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getCantGLP() {
        return cantGLP;
    }

    public void setCantGLP(int cantGLP) {
        this.cantGLP = cantGLP;
    }

    public int getCantDiesel() {
        return cantDiesel;
    }

    public void setCantDiesel(int cantDiesel) {
        this.cantDiesel = cantDiesel;
    }

    public Accidente getIdAccidente() {
        return idAccidente;
    }

    public void setIdAccidente(Accidente idAccidente) {
        this.idAccidente = idAccidente;
    }

    public Camion getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Camion idCamion) {
        this.idCamion = idCamion;
    }

    public Conductor getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Conductor idConductor) {
        this.idConductor = idConductor;
    }

    public Copiloto getIdCopiloto() {
        return idCopiloto;
    }

    public void setIdCopiloto(Copiloto idCopiloto) {
        this.idCopiloto = idCopiloto;
    }

    @XmlTransient
    public List<Arista> getAristaList() {
        return aristaList;
    }

    public void setAristaList(List<Arista> aristaList) {
        this.aristaList = aristaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRuta != null ? idRuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.idRuta == null && other.idRuta != null) || (this.idRuta != null && !this.idRuta.equals(other.idRuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ruta[ idRuta=" + idRuta + " ]";
    }
    
}
