package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Camion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(TipoCamion.class)
public class TipoCamion_ { 

    public static volatile ListAttribute<TipoCamion, Camion> camionList;
    public static volatile SingularAttribute<TipoCamion, Integer> tara;
    public static volatile SingularAttribute<TipoCamion, Integer> idTipoCamion;
    public static volatile SingularAttribute<TipoCamion, Integer> capacidadDiesel;
    public static volatile SingularAttribute<TipoCamion, Integer> capacidadGLP;

}