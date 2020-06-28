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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author metal
 */
public class Conecion {
    public static Connection obtenerConexion() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, SQLException {
        BaseDatos bd = BaseDatos.obtenerInstancia();
        Connection cnx = bd.obtenerConexion();
        return cnx;
    }
}
