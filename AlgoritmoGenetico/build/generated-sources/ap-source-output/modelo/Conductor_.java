package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Persona;
import modelo.Ruta;
import modelo.Turno;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Conductor.class)
public class Conductor_ { 

    public static volatile SingularAttribute<Conductor, String> estado;
    public static volatile ListAttribute<Conductor, Ruta> rutaList;
    public static volatile SingularAttribute<Conductor, Integer> idEmpleado;
    public static volatile SingularAttribute<Conductor, Date> fechaRegistro;
    public static volatile SingularAttribute<Conductor, Persona> idPersona;
    public static volatile SingularAttribute<Conductor, Turno> idTurno;

}