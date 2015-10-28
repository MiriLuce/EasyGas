package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Nodo;
import modelo.Ruta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Accidente.class)
public class Accidente_ { 

    public static volatile SingularAttribute<Accidente, Date> fecha;
    public static volatile SingularAttribute<Accidente, String> estado;
    public static volatile ListAttribute<Accidente, Ruta> rutaList;
    public static volatile SingularAttribute<Accidente, Integer> idAccidente;
    public static volatile SingularAttribute<Accidente, Nodo> idNodo;
    public static volatile SingularAttribute<Accidente, Date> hora;
    public static volatile SingularAttribute<Accidente, String> observacion;
    public static volatile SingularAttribute<Accidente, String> solucion;

}