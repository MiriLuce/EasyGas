package Modelo.Hibernate;
// Generated 10/11/2015 08:33:55 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Arista generated by hbm2java
 */
public class Arista implements java.io.Serializable {

    private Integer idArista;
    private Nodo nodoByIdOrigen;
    private Nodo nodoByIdDestino;
    private Ruta ruta;
    private int distancia;
    private int cantCuadras;
    private Date horaInicio;
    private Date horaFin;
    private String estado;
    private Pedido pedido;
    private int direccion; //0=derecha,1=izquierda,2=abajo,3=arriba

    public Arista() {
    }

    public Arista(int distancia, Nodo nodoByIdOrigen, Nodo nodoByIdDestino) {
        this.nodoByIdOrigen = nodoByIdOrigen;
        this.nodoByIdDestino = nodoByIdDestino;
        this.distancia = distancia;
    }
    
    public Arista(Nodo nodoByIdOrigen, Nodo nodoByIdDestino, Ruta ruta, int distancia, int cantCuadras, String estado,Pedido pedido) {
        this.nodoByIdOrigen = nodoByIdOrigen;
        this.nodoByIdDestino = nodoByIdDestino;
        this.ruta = ruta;
        this.distancia = distancia;
        this.cantCuadras = cantCuadras;
        this.estado = estado;
        this.pedido=pedido;
    }

    public Arista(Nodo nodoByIdOrigen, Nodo nodoByIdDestino, Ruta ruta, int distancia, int cantCuadras, Date horaInicio, Date horaFin, String estado) {
        this.nodoByIdOrigen = nodoByIdOrigen;
        this.nodoByIdDestino = nodoByIdDestino;
        this.ruta = ruta;
        this.distancia = distancia;
        this.cantCuadras = cantCuadras;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public int getDireccion() {
        if (nodoByIdOrigen.getCoordX() == nodoByIdDestino.getCoordX()) {//vertical
            if (nodoByIdOrigen.getCoordY() < nodoByIdDestino.getCoordY()) {
                direccion = 2;
            } else {
                direccion = 3;
            }
        } else {//horizontal
            if (nodoByIdOrigen.getCoordX() < nodoByIdDestino.getCoordX()) {
                direccion = 0;
            } else {
                direccion = 1;
            }
        }
        return direccion;
    }

    public Integer getIdArista() {
        return this.idArista;
    }

    public void setIdArista(Integer idArista) {
        this.idArista = idArista;
    }

    public Nodo getNodoByIdOrigen() {
        return this.nodoByIdOrigen;
    }

    public void setNodoByIdOrigen(Nodo nodoByIdOrigen) {
        this.nodoByIdOrigen = nodoByIdOrigen;
    }

    public Nodo getNodoByIdDestino() {
        return this.nodoByIdDestino;
    }

    public void setNodoByIdDestino(Nodo nodoByIdDestino) {
        this.nodoByIdDestino = nodoByIdDestino;
    }

    public Ruta getRuta() {
        return this.ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public int getDistancia() {
        return this.distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getCantCuadras() {
        return this.cantCuadras;
    }

    public void setCantCuadras(int cantCuadras) {
        this.cantCuadras = cantCuadras;
    }

    public Date getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
