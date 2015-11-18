/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloCompleto;

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
@Table(name = "tipo_camion", catalog = "dp1_easygas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCamion.findAll", query = "SELECT t FROM TipoCamion t"),
    @NamedQuery(name = "TipoCamion.findByIdTipoCamion", query = "SELECT t FROM TipoCamion t WHERE t.idTipoCamion = :idTipoCamion"),
    @NamedQuery(name = "TipoCamion.findByCapacidadDiesel", query = "SELECT t FROM TipoCamion t WHERE t.capacidadDiesel = :capacidadDiesel"),
    @NamedQuery(name = "TipoCamion.findByCapacidadGLP", query = "SELECT t FROM TipoCamion t WHERE t.capacidadGLP = :capacidadGLP"),
    @NamedQuery(name = "TipoCamion.findByTara", query = "SELECT t FROM TipoCamion t WHERE t.tara = :tara")})
public class TipoCamion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoCamion")
    private Integer idTipoCamion;
    @Basic(optional = false)
    @Column(name = "CapacidadDiesel")
    private int capacidadDiesel;
    @Basic(optional = false)
    @Column(name = "CapacidadGLP")
    private int capacidadGLP;
    @Basic(optional = false)
    @Column(name = "Tara")
    private int tara;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCamion", fetch = FetchType.LAZY)
    private List<Camion> camionList;

    public TipoCamion() {
    }

    public TipoCamion(Integer idTipoCamion) {
        this.idTipoCamion = idTipoCamion;
    }

    public TipoCamion(Integer idTipoCamion, int capacidadDiesel, int capacidadGLP, int tara) {
        this.idTipoCamion = idTipoCamion;
        this.capacidadDiesel = capacidadDiesel;
        this.capacidadGLP = capacidadGLP;
        this.tara = tara;
    }

    public Integer getIdTipoCamion() {
        return idTipoCamion;
    }

    public void setIdTipoCamion(Integer idTipoCamion) {
        this.idTipoCamion = idTipoCamion;
    }

    public int getCapacidadDiesel() {
        return capacidadDiesel;
    }

    public void setCapacidadDiesel(int capacidadDiesel) {
        this.capacidadDiesel = capacidadDiesel;
    }

    public int getCapacidadGLP() {
        return capacidadGLP;
    }

    public void setCapacidadGLP(int capacidadGLP) {
        this.capacidadGLP = capacidadGLP;
    }

    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
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
        hash += (idTipoCamion != null ? idTipoCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCamion)) {
            return false;
        }
        TipoCamion other = (TipoCamion) object;
        if ((this.idTipoCamion == null && other.idTipoCamion != null) || (this.idTipoCamion != null && !this.idTipoCamion.equals(other.idTipoCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoCamion[ idTipoCamion=" + idTipoCamion + " ]";
    }
    
}
