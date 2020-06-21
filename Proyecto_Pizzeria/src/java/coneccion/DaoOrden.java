/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import clases.Ingrediente;
import clases.Orden;
import clases.Pizza;
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
                for (int i = 0; i < r.size(); i++) {
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
            c.setfPago(rs.getString("formaPago"));
        } catch (SQLException ex) {

        }
        return c;
    }

    public static boolean insertarOrden(Orden guardarO, Usuario us, ArrayList<Pizza> listaPizza, ArrayList<Producto> listaProducto, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(IMEC_Usuario.INSERTARORDEN.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, String.valueOf(guardarO.getIdOrden()));
            stm.setString(2, String.valueOf(guardarO.getEstado()));
            stm.setString(3, String.valueOf(us.getId()));
            stm.setString(4, String.valueOf(guardarO.getfPago()));
            stm.executeUpdate();
            ArrayList<Pizza> pizzaO = guardarO.getListaPizzas();
            for (Pizza p : listaPizza) {
                ArrayList<Pizza> pizzasIguales = buscaPizzas(pizzaO, p);//lista de pizzas en la orden con la misma id
                ArrayList<Ingrediente> adicionales = new ArrayList<>();
                if (pizzasIguales.size() > 0) {
                    int i = 0;
                    for (Pizza pOrden : pizzasIguales) {//recorro las pizzas con la misma id
                        adicionales = buscaIngredientesAdicional(pOrden.getListaIngredientes(), p.getListaIngredientes());//obtengo la lista de ingredientes adicionales de la pizza
                        pOrden.setCantidad(pizzasIguales.size());//seteo la cantidad de pizzas con la misma id
                        i = i + pOrden.getPrecio();
                    }
                    Pizza pOrden = pizzasIguales.get(0);
                    pOrden.setPrecio(i);
                    DaoRelacionPizzaOrden.insertarOrdenPizza(guardarO, pOrden, us, cnx);//inserto la pizza de la orden
                    for (Ingrediente ingA : adicionales) {//inserto sus ingredientes adicionales
                        DaoAdicionales.insertarOrdenAdicional(guardarO, ingA, pOrden, cnx);
                    }
                }
            }
            ArrayList<Producto> ProductoO = guardarO.getListaProductos();
            for (Producto prod : listaProducto) {
                ArrayList<Producto> productosRepetidos = buscaProductos(ProductoO, prod);//productos repetidos o con la misma id
                if (productosRepetidos.size() > 0) {
                    int j = 0;
                    for (Producto prO : productosRepetidos) {//recorro cada producto repetido
                        prO.setCantidadProducto(productosRepetidos.size());//seteo la cantidad
                        j = j + prO.getPrecio();
                    }
                    Producto prO = productosRepetidos.get(0);
                    prO.setPrecio(j);
                    DaoRelacionProductoOrden.insertarOrdenProducto(guardarO, prO, us, cnx);//lo inserto en la relacion
                }
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private static ArrayList<Producto> buscaProductos(ArrayList<Producto> listaOrden, Producto producto) {
        ArrayList<Producto> respuesta = new ArrayList<>();
        for (Producto p : listaOrden) {
            if (p.getIDProducto() == producto.getIDProducto()) {
                respuesta.add(p);
            }
        }
        return respuesta;

    }

    private static ArrayList<Pizza> buscaPizzas(ArrayList<Pizza> listaOrden, Pizza pizzaMenu) {
        ArrayList<Pizza> respuesta = new ArrayList<>();
        for (Pizza p : listaOrden) {
            if (p.getPizzaID() == pizzaMenu.getPizzaID()) {
                respuesta.add(p);
            }
        }
        return respuesta;

    }

    private static ArrayList<Ingrediente> buscaIngredientesAdicional(ArrayList<Ingrediente> ingredientesPOrden, ArrayList<Ingrediente> ingredientes) {
        ArrayList<Ingrediente> respuesta = new ArrayList<>();
        boolean esExtra = true;
        for (Ingrediente ing : ingredientesPOrden) {
            for (Ingrediente ingP : ingredientes) {
                if (ingP.getIdIng() == ing.getIdIng()) {
                    esExtra = false;
                }
            }
            if (esExtra) {
                respuesta.add(ing);
            }
            esExtra = true;
        }
        return respuesta;
    }

}
