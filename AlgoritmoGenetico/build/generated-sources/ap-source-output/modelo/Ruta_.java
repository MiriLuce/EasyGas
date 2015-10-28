package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Accidente;
import modelo.Arista;
import modelo.Camion;
import modelo.Conductor;
import modelo.Copiloto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Ruta.class)
public class Ruta_ { 

    public static volatile SingularAttribute<Ruta, Accidente> idAccidente;
    public static volatile ListAttribute<Ruta, Arista> aristaList;
    public static volatile SingularAttribute<Ruta, Integer> cantGLP;
    public static volatile SingularAttribute<Ruta, Date> horaLlegada;
    public static volatile SingularAttribute<Ruta, Conductor> idConductor;
    public static volatile SingularAttribute<Ruta, Integer> cantDiesel;
    public static volatile SingularAttribute<Ruta, Date> fechaEntrega;
    public static volatile SingularAttribute<Ruta, Copiloto> idCopiloto;
    public static volatile SingularAttribute<Ruta, Camion> idCamion;
    public static volatile SingularAttribute<Ruta, Integer> idRuta;
    public static volatile SingularAttribute<Ruta, Date> horaSalida;

}