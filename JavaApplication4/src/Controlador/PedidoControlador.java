/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Constantes.EasyGas;
import Modelo.Hibernate.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.hibernate.*;

/**
 *
 * @author alulab14
 */
public class PedidoControlador {

    private static int SacaSiguienteNumeroEnString(String cad) {
        int i = 0;

        while (!Character.isDigit(cad.charAt(i))) {
            i++;
        }
        int j = i;
        while (Character.isDigit(cad.charAt(j))) {
            j++;
        }
        return Integer.parseInt(cad.substring(i, j));
    }

    public static ArrayList<Pedido> ListarPedidos() {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            String sql = "SELECT * FROM PEDIDO";
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Pedido.class);
            List aux = query.list();

            for (Iterator i = aux.iterator(); i.hasNext();) {
                Pedido p = (Pedido) i.next();
//                Cliente c = ClienteControlador.BuscaClienteId(p.getCliente().getIdCliente());
                Hibernate.initialize(p.getCliente());
//                p.setCliente(c);
//                Nodo n = NodoControlador.BuscaNodoId(p.getCliente().getNodo().getIdNodo());
                Hibernate.initialize(p.getCliente().getNodo());
//                p.getCliente().setNodo(n);
                lista.add(p);
            }

            tx.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }

        return lista;
    }

    public static ArrayList<Pedido> BuscaPedidosFiltro(ArrayList<Pedido> pedidos, JTextField nombre, JComboBox tipoDoc, JTextField nroDoc, JSpinner vol, JComboBox plazo, JComboBox estado) {
        ArrayList<Pedido> lista = new ArrayList<Pedido>(pedidos); //se crea copia de la lista total

        int i = 0;
        while (lista.size() > 0 && i < lista.size()) {
            Pedido ped = lista.get(i);
            boolean remueve = false;

            if (!nombre.getText().equalsIgnoreCase("")) {
                CharSequence cs = nombre.getText().toLowerCase();
                String aux2 = ped.getCliente().getNombres().toLowerCase();
                if (!aux2.contains(cs)) {
                    remueve = true;
                }
            }

            if (tipoDoc.getSelectedIndex() != 0) {
                int indice = tipoDoc.getSelectedIndex();
                if ((indice == 1 && ped.getCliente().getTipoDocumento().equalsIgnoreCase("RUC")) || (indice == 2 && ped.getCliente().getTipoDocumento().equalsIgnoreCase("DNI"))) {
                    remueve = true;
                }
            }

            if (!nroDoc.getText().equalsIgnoreCase("")) {
                if (!nroDoc.getText().equalsIgnoreCase(ped.getCliente().getNroDocumento())) {
                    remueve = true;
                }
            }

            if ((Double) vol.getValue() > 0) {
                if (ped.getCantGlp() > (Double) vol.getValue()) {
                    remueve = true;
                }
            }

            if (plazo.getSelectedIndex() != 0) {
                String p = plazo.getSelectedItem().toString();
                int plazoAux = GeneralControlador.SacaSiguienteNumeroEnString(p);

                if (ped.getPlazo() != plazoAux) {
                    remueve = true;
                }
            }

            if (estado.getSelectedIndex() != 0) {
                String e = estado.getSelectedItem().toString();
                if (!ped.getEstado().equalsIgnoreCase(e)) {
                    remueve = true;
                }
            }

            if (remueve) {
                lista.remove(i);
            } else {
                i++;
            }
        }

        return lista;
    }

    public static Pedido BuscaPedidoId(Integer pedidoId) {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        String sql = "SELECT * FROM PEDIDO WHERE idPedido = :idpedido";

        try {
            tx = EasyGas.sesion.beginTransaction();

            //query SQL
            SQLQuery query = EasyGas.sesion.createSQLQuery(sql);
            query.addEntity(Pedido.class);
            query.setParameter("idpedido", pedidoId);
            List aux = query.list();

            tx.commit();

            for (Iterator i = aux.iterator(); i.hasNext();) {
                Pedido p = (Pedido) i.next();
                Cliente c = ClienteControlador.BuscaClienteId(p.getCliente().getIdCliente());
                p.setCliente(c);
                Nodo n = NodoControlador.BuscaNodoId(p.getCliente().getNodo().getIdNodo());
                p.getCliente().setNodo(n);
                lista.add(p);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }

        return lista.get(0);
    }

    private static String ObtenDireccionNodo(Nodo n) {

        String s = "(" + n.getCoordX() + "," + n.getCoordY() + ")";

        return s;
    }

    public static void ActualizaTablaPedidos(ArrayList<Pedido> lista, JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm");

        for (int i = 0; i < lista.size(); i++) {
            Object[] fila = new Object[6];
            fila[0] = lista.get(i).getIdPedido();
            fila[1] = lista.get(i).getCliente().getNombres();
            fila[2] = ObtenDireccionNodo(lista.get(i).getCliente().getNodo());
            fila[3] = sdf.format(lista.get(i).getHoraSolicitada());
            fila[4] = lista.get(i).getCantGlp();
            fila[5] = lista.get(i).getEstado();
            modelo.addRow(fila);
        }
    }

    public static boolean TienePrioridad(Cliente cliente) {
        int turno = 0;

        Calendar calAux = Calendar.getInstance();
        int horAux = calAux.get(Calendar.HOUR_OF_DAY);

        if (horAux < 8) {
            turno = 1;
        } else if (horAux < 16) {
            turno = 2;
        } else {
            turno = 3;
        }

        if (cliente.getTipoDocumento().equalsIgnoreCase("DNI") && turno == 1) {
            return true;
        } else if (cliente.getTipoDocumento().equalsIgnoreCase("RUC") && turno == 3) {
            return true;
        }

        return false;
    }

    public static void GuardarPedidos(ArrayList<Pedido> lista) {
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();

            for (int i = 0; i < lista.size(); i++) {
                int idPedido = (int) EasyGas.sesion.save(lista.get(i));
                lista.get(i).setIdPedido(idPedido);
            }

            tx.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }
    }

    public static void GuardarPedido(Pedido nuevoPed) {
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();
            EasyGas.sesion.saveOrUpdate(nuevoPed);
            tx.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }
    }

    public static void EliminarPedido(Integer pedidoId) {
        if (!EasyGas.sesion.isOpen()) {
            EasyGas.sesion = EasyGas.sesFact.openSession();
        }

        Transaction tx = null;

        try {
            tx = EasyGas.sesion.beginTransaction();
            Pedido ped = (Pedido) EasyGas.sesion.get(Pedido.class, pedidoId);
            ped.setEstado("ANULADO");
            EasyGas.sesion.saveOrUpdate(ped);
            tx.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión");
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (EasyGas.sesion.isOpen()) {
                EasyGas.sesion.close();
            }
        }
    }

    public static ArrayList<Pedido> CargaPedidosArchivo(String rutaArchivo) {
        BufferedReader br = null;
        String linea = "";
        String cvsDiv = ",";

        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        try {

            br = new BufferedReader(new FileReader(rutaArchivo));
            while ((linea = br.readLine()) != null) {

                //se usa punto y coma de separador
                String[] datosPed = linea.split(cvsDiv);

                String cadAux = GeneralControlador.DevuelveFormatoDireccion(Integer.parseInt(datosPed[2]), Integer.parseInt(datosPed[3]));
                ArrayList<Integer> aux = GeneralControlador.SacaCoordinadas(cadAux);

                Cliente c = ClienteControlador.BuscaClienteDireccion(ClienteControlador.ListarClientes(), aux);
                int p = Integer.parseInt(datosPed[1]);
                double cantGLP = Double.parseDouble(datosPed[0]);

                String prioridad;

                if (PedidoControlador.TienePrioridad(c)) {
                    prioridad = "SI";
                } else {
                    prioridad = "NO";
                }

                Calendar cal = Calendar.getInstance(); //fecha y hora actual de registro
                Date ahora = cal.getTime();
                cal.add(Calendar.HOUR, p);
                Date horaSol = cal.getTime();

                Pedido ped = new Pedido(c, ahora, horaSol, cantGLP, p, prioridad);

                lista.add(ped);

                GuardarPedido(ped);
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }

        return lista;
    }

    private static Date FormaFechaRegistro(String fecha, String hora) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
        String nuevaFecha = "" + fecha + " " + hora;
        return sdf.parse(nuevaFecha);
    }

    public static ArrayList<Pedido> CargaPedidosSimulacion(String rutaArchivo) throws FileNotFoundException, IOException, ParseException {

        System.out.println(rutaArchivo);

        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        FileInputStream fistream = new FileInputStream(rutaArchivo);
        DataInputStream in = new DataInputStream(fistream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        int indice = 0;
        while ((line = br.readLine()) != null) {
            String pedido[] = line.split(";");

            Cliente c = ClienteControlador.BuscaClienteId(Integer.parseInt(pedido[2]));

            Date fechaRegistro = FormaFechaRegistro(pedido[0], pedido[1]);

            int p = Integer.parseInt(pedido[4].substring(1));

            if (p == 99) { //p99
                p = 48;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaRegistro);
            cal.add(Calendar.HOUR, p);
            Date horaSolicitada = cal.getTime();

            int cantGLP = Integer.parseInt(pedido[3]);

            String prioridad;

            if (PedidoControlador.TienePrioridad(c)) {
                prioridad = "SI";
            } else {
                prioridad = "NO";
            }

            Pedido ped = new Pedido(c, fechaRegistro, horaSolicitada, cantGLP, p, prioridad);
            ped.setIdPedido(indice);
            lista.add(ped);
            indice++;
        }

        return lista;
    }
}
