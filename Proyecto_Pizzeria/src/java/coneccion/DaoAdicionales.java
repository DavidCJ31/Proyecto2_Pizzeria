
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


public class DaoAdicionales {
    public static ArrayList<Ingrediente> obtenerIngredienteAdicionales(String idPizza, String idOrden, Connection cnx) {
        ArrayList<Ingrediente> r = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARINGREDIENTESADICIONALES.obtenerComando());) {
            stm.clearParameters();
             stm.setString(1, idPizza);
             stm.setString(1, idOrden);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getIngredienteAdicionales(rs, cnx));
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
     public static Ingrediente getIngredienteAdicionales(ResultSet rs, Connection conn) {
        Ingrediente c = new Ingrediente();
        try {
            c.setIdIng(rs.getInt("ingrediente"));
        } catch (SQLException ex) {

        }
        return c;
    }
     
     public static boolean insertarOrdenAdicional(Orden guardarO, Ingrediente img, Pizza pi, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.INSERTARINGREDIENTESADICIONALES.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(pi.getPizzaID()));
            stm.setString(2, String.valueOf(img.getIdIng()));
            stm.setString(3, String.valueOf(guardarO.getIdOrden()));
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
