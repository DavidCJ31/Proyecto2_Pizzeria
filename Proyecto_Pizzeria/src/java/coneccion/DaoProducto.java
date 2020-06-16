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
public class DaoProducto {

    public static Producto obtenerProducto(String id, Connection cnx) {
        Producto r = new Producto();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.CONSULTARPRODUCTO.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = (new Producto(
                            rs.getInt("precio"),
                            rs.getString("descripcion"),
                            rs.getInt("ID"),
                            0,
                            rs.getString("nombre")
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
}
