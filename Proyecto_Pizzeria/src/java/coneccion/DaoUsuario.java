/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

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
public class DaoUsuario {
    
     public static Usuario obtenerUsuario(String contra, String id, Connection cnx) {
        Usuario r = null;
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, contra);
             stm.setString(2, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = (new Usuario(
                            rs.getString("id"),
                            rs.getString("password"),
                           rs.getString("tipo"),
                           new ArrayList<>(),
                           rs.getString("apellido1"),
                           rs.getString("apellido2"),
                           rs.getString("direccion"),
                            rs.getString("telefono")
                    ));
                }
                r.setListaOrdenes(DaoOrden.obtenerOrdenesUsuario(r.getId(), cnx));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
    
}
