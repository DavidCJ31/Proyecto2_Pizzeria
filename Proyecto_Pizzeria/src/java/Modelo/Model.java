/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import clases.Usuario;
import coneccion.Conecion;
import coneccion.DaoUsuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author metal
 */
public class Model {
     
    private static Model uniqueInstance;
    private static Connection conn;

    public static void setConn(Connection conn) {
        Model.conn = conn;
    }
    
    
    
    private static Connection connect() throws SQLException{
         try {
             return Conecion.obtenerConexion();
         }catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException x) {
            Logger.getLogger(Conecion.class.getName()).log(Level.SEVERE, null, x);
            return null;
        }
    }
    
    public static Model instance() {
        if (uniqueInstance == null) {
            try {
                uniqueInstance = new Model();
                setConn(connect());
            } catch (SQLException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uniqueInstance;
    }
    
    public static boolean InsertarUsuario(Usuario us){
        return DaoUsuario.insertarUsuario(us, conn);
    }
    public static Usuario ObtenerUsuario(String contrs, String id){
        return DaoUsuario.obtenerUsuario(contrs, id, conn);
    }
}
