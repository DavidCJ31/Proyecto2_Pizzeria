/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import clases.Orden;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author metal
 */
public class DaoOrden {
    
    
    public static ArrayList<Orden> obtenerOrdenesUsuario(String id, Connection cnx) {
        ArrayList<Orden> r = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.LISTAR.obtenerComando());) {
            stm.clearParameters();
             stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getOrden(rs, cnx));
                }
                for(int i = 0; i < r.size(); i++){
                    Orden o = r.get(i);
                    o.setListaPizzas(DaoRelacionPizzaOrden.obtenerPizzaOrden(String.valueOf(o.getIdOrden()), cnx));
                    o.setListaProductos(DaoRelacionProductoOrden.obtenerProductoOrden(String.valueOf(o.getIdOrden()), cnx));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
     public static Orden getOrden(ResultSet rs, Connection conn) {
        Orden c = new Orden();
        try {
            c.setEstado(rs.getString("estado"));
            c.setFecha(rs.getDate("fecha"));
            c.setIdOrden(rs.getInt("id"));
        } catch (SQLException ex) {

        }
        return c;
    }
    
}
