/*
 <%-- 
 
// // EIF209 - Programación 4 – Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  -  
//  - 
// // --%> 
 */
package coneccion;

import clases.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author metal
 */
public class DaoIngrediente {
    public static Ingrediente obtenerIngrediente(String id, Connection cnx) {
        Ingrediente r = null;
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.CONSULTARING.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = (new Ingrediente(
                            rs.getString("nombre"),
                            rs.getInt("precio"),
                            rs.getInt("ID")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
    public static ArrayList<Ingrediente> obtenerListaIngredientes(Connection cnx) {
         ArrayList<Ingrediente> lista = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARING.obtenerComando());) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                  Ingrediente  r = (new Ingrediente(
                            rs.getString("nombre"),
                            rs.getInt("precio"),
                            rs.getInt("ID")
                    ));
                  lista.add(r);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
}
