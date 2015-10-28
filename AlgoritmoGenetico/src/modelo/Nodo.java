/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "nodo", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nodo.findAll", query = "SELECT n FROM Nodo n"),
    @NamedQuery(name = "Nodo.findByIdNodo", query = "SELECT n FROM Nodo n WHERE n.idNodo = :idNodo"),
    @NamedQuery(name = "Nodo.findByCoordX", query = "SELECT n FROM Nodo n WHERE n.coordX = :coordX"),
    @NamedQuery(name = "Nodo.findByCoordY", query = "SELECT n FROM Nodo n WHERE n.coordY = :coordY"),
    @NamedQuery(name = "Nodo.findByHabilitado", query = "SELECT n FROM Nodo n WHERE n.habilitado = :habilitado")})
public class Nodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNodo")
    private Integer idNodo;
    @Basic(optional = false)
    @Column(name = "CoordX")
    private int coordX;
    @Basic(optional = false)
    @Column(name = "CoordY")
    private int coordY;
    @Column(name = "Habilitado")
    private String habilitado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNodo", fetch = FetchType.LAZY)
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrigen", fetch = FetchType.LAZY)
    private List<Arista> aristaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDestino", fetch = FetchType.LAZY)
    private List<Arista> aristaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNodo", fetch = FetchType.LAZY)
    private List<Accidente> accidenteList;

    public Nodo() {
    }

    public Nodo(Integer idNodo) {
        this.idNodo = idNodo;
    }

    public Nodo(Integer idNodo, int coordX, int coordY) {
        this.idNodo = idNodo;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Integer getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(Integer idNodo) {
        this.idNodo = idNodo;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Arista> getAristaList() {
        return aristaList;
    }

    public void setAristaList(List<Arista> aristaList) {
        this.aristaList = aristaList;
    }

    @XmlTransient
    public List<Arista> getAristaList1() {
        return aristaList1;
    }

    public void setAristaList1(List<Arista> aristaList1) {
        this.aristaList1 = aristaList1;
    }

    @XmlTransient
    public List<Accidente> getAccidenteList() {
        return accidenteList;
    }

    public void setAccidenteList(List<Accidente> accidenteList) {
        this.accidenteList = accidenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNodo != null ? idNodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodo)) {
            return false;
        }
        Nodo other = (Nodo) object;
        if ((this.idNodo == null && other.idNodo != null) || (this.idNodo != null && !this.idNodo.equals(other.idNodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Nodo[ idNodo=" + idNodo + " ]";
    }
    
}
