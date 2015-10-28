package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Camion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Mantenimiento.class)
public class Mantenimiento_ { 

    public static volatile SingularAttribute<Mantenimiento, Date> fecha;
    public static volatile SingularAttribute<Mantenimiento, String> estado;
    public static volatile SingularAttribute<Mantenimiento, Integer> cantTurnos;
    public static volatile SingularAttribute<Mantenimiento, Integer> idTipoMantenimiento;
    public static volatile SingularAttribute<Mantenimiento, Date> hora;
    public static volatile SingularAttribute<Mantenimiento, Integer> idMantenimiento;
    public static volatile ListAttribute<Mantenimiento, Camion> camionList;

}