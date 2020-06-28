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

import clases.Orden;
import clases.Producto;
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
public class DaoRelacionProductoOrden {

    public static ArrayList<Producto> obtenerProductoOrden(String id, Connection cnx) {
        ArrayList<Producto> r = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTARPRODUCTOORDEN.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getProductoOrden(rs, cnx));
                }
                for (int i = 0; i < r.size(); i++) {
                    Producto o = r.get(i);
                    Producto p = DaoProducto.obtenerProducto(String.valueOf(o.getIDProducto()), cnx);
                    o.setNombre(p.getNombre());
                    o.setPrecio(p.getPrecio());
                    o.setDescripcion(p.getDescripcion());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }

    public static Producto getProductoOrden(ResultSet rs, Connection conn) {
        Producto c = new Producto();
        try {
            c.setIDProducto(rs.getInt("producto"));
            c.setCantidadProducto(rs.getInt("cantidad"));
        } catch (SQLException ex) {

        }
        return c;
    }

    public static boolean insertarOrdenProducto(Orden guardarO, Producto pi, Usuario us, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.INSERTARPRODUCTOORDEN.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(guardarO.getIdOrden()));
            stm.setString(2, String.valueOf(pi.getCantidadProducto()));
            stm.setString(3, String.valueOf(pi.getIDProducto()));
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
