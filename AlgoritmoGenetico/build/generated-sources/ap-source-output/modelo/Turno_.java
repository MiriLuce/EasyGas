package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Conductor;
import modelo.Copiloto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Turno.class)
public class Turno_ { 

    public static volatile SingularAttribute<Turno, Date> horaFin;
    public static volatile ListAttribute<Turno, Copiloto> copilotoList;
    public static volatile ListAttribute<Turno, Conductor> conductorList;
    public static volatile SingularAttribute<Turno, Integer> idTurno;
    public static volatile SingularAttribute<Turno, Date> horaInicio;

}