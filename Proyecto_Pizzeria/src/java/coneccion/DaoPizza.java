/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import clases.Pizza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author metal
 */
public class DaoPizza {
    
    
    public static Pizza obtenerPizza(String id, Connection cnx) {
        Pizza r = new Pizza();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARPIZZA.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = (new Pizza(
                            rs.getString("nombre"),
                            rs.getString("ID"),
                            rs.getInt("precio"),
                            new ArrayList<>(),
                            0,
                            rs.getInt("id")
                    ));
                }
                r.setListaIngredientes(DaoRelacionPizzaIngrediente.obtenerIngredientePizza(String.valueOf(r.getPizzaID()), cnx));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
}
