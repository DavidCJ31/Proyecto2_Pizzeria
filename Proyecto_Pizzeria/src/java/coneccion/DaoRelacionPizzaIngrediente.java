/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class DaoRelacionPizzaIngrediente {
     public static ArrayList<Ingrediente> obtenerIngredientePizza(String id, Connection cnx) {
        ArrayList<Ingrediente> r = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARINGREDIENTESPIZZA.obtenerComando());) {
            stm.clearParameters();
             stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getIngredientePizza(rs, cnx));
                }
                for(int i = 0; i < r.size(); i++){
                    Ingrediente o = r.get(i);
                    Ingrediente p = DaoIngrediente.obtenerIngrediente(String.valueOf(o.getIdIng()), cnx);
                   o = p;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
     public static Ingrediente getIngredientePizza(ResultSet rs, Connection conn) {
        Ingrediente c = new Ingrediente();
        try {
            c.setIdIng(rs.getInt("ingrediente"));
        } catch (SQLException ex) {

        }
        return c;
    }
}
