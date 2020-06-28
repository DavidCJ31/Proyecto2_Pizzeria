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

import clases.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class DaoComentario {

    public static ArrayList<Comentario> obtenerListaComentarios(Connection cnx) {
        ArrayList<Comentario> res = new ArrayList<>();
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.CONSULTAR_COMENTARIOS.obtenerComando());) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Comentario r = (new Comentario(
                            rs.getString("usuario"),
                            rs.getString("descripcion")
                    ));
                    r.setFecha(rs.getDate("fecha"));
                    res.add(r);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
        public static boolean insertarComentario(Comentario coment, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.AGREGAR_COMENTARIO.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(coment.getIdUsuario()));
            stm.setString(2, String.valueOf(coment.getDescripcion()));
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
