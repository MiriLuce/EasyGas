package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Nodo;
import modelo.Ruta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Arista.class)
public class Arista_ { 

    public static volatile SingularAttribute<Arista, Date> horaFin;
    public static volatile SingularAttribute<Arista, String> estado;
    public static volatile SingularAttribute<Arista, Nodo> idOrigen;
    public static volatile SingularAttribute<Arista, Integer> distancia;
    public static volatile SingularAttribute<Arista, Integer> cantCuadras;
    public static volatile SingularAttribute<Arista, Integer> idArista;
    public static volatile SingularAttribute<Arista, Date> horaInicio;
    public static volatile SingularAttribute<Arista, Nodo> idDestino;
    public static volatile SingularAttribute<Arista, Ruta> idRuta;

}