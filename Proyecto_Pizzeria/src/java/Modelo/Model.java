/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import clases.Ingrediente;
import clases.Pizza;
import clases.Usuario;
import coneccion.Conecion;
import coneccion.DaoIngrediente;
import coneccion.DaoPizza;
import coneccion.DaoRelacionPizzaIngrediente;
import coneccion.DaoUsuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public static ArrayList<Pizza> ObtenerListaPizzas(){
        return DaoPizza.obtenerListaPizzas(conn);
    }
    public static ArrayList<Ingrediente> ObtenerListaIngredientes(){
        return DaoIngrediente.obtenerListaIngredientes(conn);
    }
    
    public static boolean AgregarPizza(Pizza pizza){
        return DaoPizza.agregarPizza(pizza, conn);
    }
    
    public static boolean EliminarPizza(int pizza){
        DaoRelacionPizzaIngrediente.eliminarIngrediente(pizza, conn);
        return DaoPizza.eliminarPizza(pizza, conn);
    }

    public static boolean ModificarUsuario(Usuario us){
        return DaoUsuario.modificarUsuario(us,conn);
    }
    
}
