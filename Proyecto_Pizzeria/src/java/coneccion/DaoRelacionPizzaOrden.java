/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import clases.Ingrediente;
import clases.Orden;
import clases.Pizza;
import clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author metal
 */
public class DaoRelacionPizzaOrden {
    public static ArrayList<Pizza> obtenerPizzaOrden(String id, Connection cnx) {
        ArrayList<Pizza> r = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARPIZZAORDEN.obtenerComando());) {
            stm.clearParameters();
             stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getPizzaOrden(rs, cnx));
                }
                for(int i = 0; i < r.size(); i++){
                    Pizza o = r.get(i);
                    Pizza p = DaoPizza.obtenerPizza(String.valueOf(o.getPizzaID()), cnx);
                    ArrayList<Ingrediente> ad = DaoAdicionales.obtenerIngredienteAdicionales(String.valueOf(p.getPizzaID()), id, cnx);
                    o.setNombre(p.getNombre());
                    p.getListaIngredientes().addAll(ad);
                    o.setListaIngredientes(p.getListaIngredientes());
                    o.setPrecio(p.getPrecio());
                    o.setTamanno(p.getTamanno());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
     public static Pizza getPizzaOrden(ResultSet rs, Connection conn) {
        Pizza c = new Pizza();
        try {
            c.setCantidad(rs.getInt("cantidad"));
            c.setPizzaID(rs.getInt("pizza"));
            c.setTamanno("tamano");
        } catch (SQLException ex) {

        }
        return c;
    }
    public static boolean insertarOrdenPizza(Orden guardarO, Pizza pi, Usuario us, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.INSERTARPIZZAORDEN.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(guardarO.getIdOrden()));
            stm.setString(2, String.valueOf(pi.getPizzaID()));
            stm.setString(3, String.valueOf(pi.getCantidad()));
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
