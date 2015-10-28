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
@StaticMetamodel(Copiloto.class)
public class Copiloto_ { 

    public static volatile SingularAttribute<Copiloto, String> estado;
    public static volatile ListAttribute<Copiloto, Ruta> rutaList;
    public static volatile SingularAttribute<Copiloto, Date> fechaRegistro;
    public static volatile SingularAttribute<Copiloto, Integer> idCopiloto;
    public static volatile SingularAttribute<Copiloto, Persona> idPersona;
    public static volatile SingularAttribute<Copiloto, Turno> idTurno;

}