package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Mantenimiento;
import modelo.Ruta;
import modelo.TipoCamion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Camion.class)
public class Camion_ { 

    public static volatile SingularAttribute<Camion, String> estado;
    public static volatile ListAttribute<Camion, Ruta> rutaList;
    public static volatile SingularAttribute<Camion, Date> fechaRegistro;
    public static volatile SingularAttribute<Camion, Mantenimiento> idMantenimiento;
    public static volatile SingularAttribute<Camion, TipoCamion> idTipoCamion;
    public static volatile SingularAttribute<Camion, Integer> idCamion;
    public static volatile SingularAttribute<Camion, String> placa;

}