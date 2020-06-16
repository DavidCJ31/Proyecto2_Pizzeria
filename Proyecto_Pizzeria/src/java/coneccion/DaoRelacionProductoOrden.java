/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import clases.Producto;
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
                for(int i = 0; i < r.size(); i++){
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
}
