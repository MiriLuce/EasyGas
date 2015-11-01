/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloCompleto;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "arista", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arista.findAll", query = "SELECT a FROM Arista a"),
    @NamedQuery(name = "Arista.findByIdArista", query = "SELECT a FROM Arista a WHERE a.idArista = :idArista"),
    @NamedQuery(name = "Arista.findByDistancia", query = "SELECT a FROM Arista a WHERE a.distancia = :distancia"),
    @NamedQuery(name = "Arista.findByCantCuadras", query = "SELECT a FROM Arista a WHERE a.cantCuadras = :cantCuadras"),
    @NamedQuery(name = "Arista.findByHoraInicio", query = "SELECT a FROM Arista a WHERE a.horaInicio = :horaInicio"),
    @NamedQuery(name = "Arista.findByHoraFin", query = "SELECT a FROM Arista a WHERE a.horaFin = :horaFin"),
    @NamedQuery(name = "Arista.findByEstado", query = "SELECT a FROM Arista a WHERE a.estado = :estado")})
public class Arista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArista")
    private Integer idArista;
    @Basic(optional = false)
    @Column(name = "Distancia")
    private int distancia;
    @Basic(optional = false)
    @Column(name = "CantCuadras")
    private int cantCuadras;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Column(name = "HoraFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "idOrigen", referencedColumnName = "idNodo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nodo idOrigen;
    @JoinColumn(name = "idDestino", referencedColumnName = "idNodo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nodo idDestino;
    @JoinColumn(name = "idRuta", referencedColumnName = "idRuta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ruta idRuta;

    public Arista() {
    }

    public Arista(Integer idArista) {
        this.idArista = idArista;
    }

    public Arista(Integer idArista, int distancia, int cantCuadras, String estado) {
        this.idArista = idArista;
        this.distancia = distancia;
        this.cantCuadras = cantCuadras;
        this.estado = estado;
    }

    public Integer getIdArista() {
        return idArista;
    }

    public void setIdArista(Integer idArista) {
        this.idArista = idArista;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getCantCuadras() {
        return cantCuadras;
    }

    public void setCantCuadras(int cantCuadras) {
        this.cantCuadras = cantCuadras;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Nodo getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Nodo idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Nodo getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Nodo idDestino) {
        this.idDestino = idDestino;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        this.idRuta = idRuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArista != null ? idArista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arista)) {
            return false;
        }
        Arista other = (Arista) object;
        if ((this.idArista == null && other.idArista != null) || (this.idArista != null && !this.idArista.equals(other.idArista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Arista[ idArista=" + idArista + " ]";
    }
    
}
