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
                            rs.getString("telefono"),
                            rs.getString("nombre")
                    ));
                }
                r.setListaOrdenes(DaoOrden.obtenerOrdenesUsuario(r.getId(), cnx));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
    
    public static boolean insertarUsuario(Usuario us, Connection conn) {
        try (PreparedStatement stm = conn.prepareStatement(IMEC_Usuario.INSERTARUSUARIO.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(us.getId()));
            stm.setString(2, String.valueOf(us.getTipo()));
            stm.setString(3, String.valueOf(us.getClave_acceso()));
            stm.setString(4, String.valueOf(us.getNombre()));
            stm.setString(5, String.valueOf(us.getApellido_1()));
            stm.setString(6, String.valueOf(us.getApellido_2()));
            stm.setString(7, String.valueOf(us.getDireccion()));
            stm.setString(8, String.valueOf(us.getTelefono()));
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }    
    public static boolean modificarUsuario(Usuario us, Connection conn) {
        try (PreparedStatement stm = conn.prepareStatement(IMEC_Usuario.MODIFICARUSUARIO.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(us.getClave_acceso()));
            stm.setString(2, String.valueOf(us.getDireccion()));
            stm.setString(3, String.valueOf(us.getTelefono()));
            stm.setString(4, String.valueOf(us.getId()));
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
