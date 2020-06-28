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
                            rs.getInt("precio"),
                            new ArrayList<>(),
                            0,
                            rs.getInt("ID")
                    ));
                }
                r.setListaIngredientes(DaoRelacionPizzaIngrediente.obtenerIngredientePizza(String.valueOf(r.getPizzaID()), cnx));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }

    public static ArrayList<Pizza> obtenerListaPizzas(Connection cnx) {
        ArrayList<Pizza> lista = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARPIZZAS.obtenerComando());) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Pizza r = new Pizza();
                    r = (new Pizza(
                            rs.getString("nombre"),
                            rs.getInt("precio"),
                            new ArrayList<>(),
                            0,
                            rs.getInt("ID")
                    ));
                    r.setTamanno(rs.getString("tamano"));
                    lista.add(r);
                }
                for (int i = 0; i < lista.size(); i++) {
                    Pizza r = lista.get(i);
                    r.setListaIngredientes(DaoRelacionPizzaIngrediente.obtenerIngredientePizza(String.valueOf(r.getPizzaID()), cnx));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public static boolean agregarPizza(Pizza pizza, Connection cnx) {

        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.INSERTARPIZZA.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(pizza.getPizzaID()));
            stm.setString(2, String.valueOf(pizza.getNombre()));
            stm.setString(3, String.valueOf(pizza.getPrecio()));
            stm.setString(4, String.valueOf(pizza.getTamanno()));
            stm.executeUpdate();
            for(Ingrediente i: pizza.getListaIngredientes()){
                DaoRelacionPizzaIngrediente.agregarIngrediente( String.valueOf(pizza.getPizzaID()), i, cnx);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean eliminarPizza(int id, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.EXCLUIRPIZZA.obtenerComando());) {
            stm.clearParameters();
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
}
