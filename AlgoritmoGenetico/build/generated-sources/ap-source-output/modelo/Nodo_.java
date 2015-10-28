package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Accidente;
import modelo.Arista;
import modelo.Cliente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-27T23:28:20")
@StaticMetamodel(Nodo.class)
public class Nodo_ { 

    public static volatile ListAttribute<Nodo, Cliente> clienteList;
    public static volatile SingularAttribute<Nodo, Integer> coordX;
    public static volatile SingularAttribute<Nodo, Integer> idNodo;
    public static volatile SingularAttribute<Nodo, Integer> coordY;
    public static volatile ListAttribute<Nodo, Arista> aristaList;
    public static volatile ListAttribute<Nodo, Arista> aristaList1;
    public static volatile ListAttribute<Nodo, Accidente> accidenteList;
    public static volatile SingularAttribute<Nodo, String> habilitado;

}