package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Conductor;
import modelo.Copiloto;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Integer> idPersonaNatural;
    public static volatile SingularAttribute<Persona, String> apellidoPaterno;
    public static volatile ListAttribute<Persona, Usuario> usuarioList;
    public static volatile SingularAttribute<Persona, Date> fechaNacimiento;
    public static volatile ListAttribute<Persona, Copiloto> copilotoList;
    public static volatile ListAttribute<Persona, Conductor> conductorList;
    public static volatile SingularAttribute<Persona, Integer> nombre;
    public static volatile SingularAttribute<Persona, String> dni;
    public static volatile SingularAttribute<Persona, String> apellidoMaterno;

}