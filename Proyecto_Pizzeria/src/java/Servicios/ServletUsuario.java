/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelo.Model;
import clases.Ingrediente;
import clases.Orden;
import clases.Pizza;
import clases.Producto;
import clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.google.gson.Gson;

/**
 *
 * @author metal
 */
@MultipartConfig
@WebServlet(name = "ServletUsuario", urlPatterns = {"/logIn", "/insertarOrden", "/Regisistrar", "/CrearPizza", "/EliminarPizza", "/ModificarUsuario"})
public class ServletUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        if (request.getServletPath().equals("/logIn")) {
            this.logIn(request, response);
        }
        if (request.getServletPath().equals("/Regisistrar")) {
            this.Regisistrar(request, response);
        }
        if (request.getServletPath().equals("/CrearPizza")) {
            this.AgregarPizza(request, response);
        }
        if (request.getServletPath().equals("/EliminarPizza")) {
            this.eliminarPizza(request, response);
        }
        if (request.getServletPath().equals("/insertarOrden")) {
            this.insertarOrden(request, response);
        }
        if (request.getServletPath().equals("/ModificarUsuario")) {
            this.modificarUsuario(request, response);
        }
    }

    private Optional<String> encoding;

    private String toUTF8String(String s) throws UnsupportedEncodingException {
        return encoding.isPresent() ? s : new String(s.getBytes(), StandardCharsets.UTF_8);
    }

    protected void insertarOrden(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        encoding = Optional.of(request.getCharacterEncoding());
        System.out.printf("request character encoding: '%s'%n", encoding.get());
        response.setContentType("application/json;charset=UTF-8");
//        String carritoPZ = request.getParameter("CarritoPizzas");
//        String carritoPD = request.getParameter("CarritoProductos");
        String pago = "pagoEfectivo";
        ArrayList<Pizza> listaP = new ArrayList<>();
        ArrayList<Producto> listaPR = new ArrayList<>();
        try (PrintWriter out = response.getWriter()) {
            StringBuilder r = new StringBuilder();
            int i = 0;
            while (request.getParameter("pizza" + i) != null) {
                JSONObject obj = new JSONObject(toUTF8String(request.getParameter("pizza" + i)));
                Pizza pizza = new Pizza(obj.getString("nombre"), obj.getString("tamano"), obj.getInt("precio"), obj.getInt("id"));
                int j = 0;
                while (request.getParameter("ingrediente" + j + "pizza" + i) != null) {
                    JSONObject obj3 = new JSONObject(toUTF8String(request.getParameter("ingrediente" + j + "pizza" + i)));
                    Ingrediente dato = new Ingrediente();
                    dato.setIdIng(obj3.getInt("id"));
                    dato.setNombre(obj3.getString("nombre"));
                    dato.setPrecio(obj3.getInt("precio"));
                    pizza.getListaIngredientes().add(dato);
                    j++;
                }
                i++;
                listaP.add(pizza);
            }
            int k = 0;
            while (request.getParameter("producto" + k) != null) {
                // Producto pr = new Gson().fromJson(toUTF8String(request.getParameter("producto" + k)), Producto.class);
                JSONObject obj2 = new JSONObject(toUTF8String(request.getParameter("producto" + k)));
                Producto pr = new Producto(obj2.getInt("precio"), obj2.getString("descripcion"), obj2.getInt("id"), obj2.getString("nombre"));
                listaPR.add(pr);
                k++;
            }
        }
        Usuario us = (Usuario) request.getSession(true).getAttribute("Usuario");
        Orden ordenGuardar = new Orden(pago, "En Preparacion", us.getListaOrdenes().size() + 1, listaP, listaPR);
        us.getListaOrdenes().add(ordenGuardar);
        ArrayList<Pizza> listaPi = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        ArrayList<Producto> listProduct = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
        boolean insertado = Model.insertarOrdenDeUsuario(ordenGuardar, us, listaPi, listProduct);
        if (insertado) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/Vistas/Menu.jsp");
            dispatcher.forward(request, response);
        } else {//error
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/Vistas/VistaPrincipal.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void logIn(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String clave = request.getParameter("password");
        String id = request.getParameter("cedula");
        String rol = request.getParameter("rol");
        Usuario us = Model.instance().ObtenerUsuario(clave, id);
        if (us.getTipo().equals(rol)) {
            request.getSession(true).setAttribute("Usuario", us);
            ArrayList<Pizza> listaPizzas = Model.instance().ObtenerListaPizzas();
            request.getSession().setAttribute("listaPizzas", listaPizzas);
            ArrayList<Producto> listaProductos = Model.instance().ObtenerListaProductos();
            request.getSession().setAttribute("listaProductos", listaProductos);
            ArrayList<Ingrediente> listaIngredientes = Model.instance().ObtenerListaIngredientes();
            request.getSession().setAttribute("listaIngrediente", listaIngredientes);
            if (rol.equals("Cliente")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(
                        "/Vistas/Menu.jsp");
                dispatcher.forward(request, response);
            } else {
                Model.instance().actualizarOrdenesEnPreparacion();
                ArrayList<Orden> listaOrden = Model.instance().ObtenerListaOrden();
                request.getSession().setAttribute("listaOrden", listaOrden);
                RequestDispatcher dispatcher = request.getRequestDispatcher(
                        "/Vistas/VistaAdministrador.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/Vistas/VistaPrincipal.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void Regisistrar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String clave = request.getParameter("contrasena1Usuario");
        String id = request.getParameter("idUsuario");
        String rol = request.getParameter("rol");
        String nombre = request.getParameter("nombreUsuario");
        String ap1 = request.getParameter("apellido1Usuario");
        String ap2 = request.getParameter("apellido2Usuario");
        String direcc = request.getParameter("direccion");
        String telefono = request.getParameter("tel1Usuario");
        Usuario ingresar = new Usuario(id, clave, rol, new ArrayList<>(), ap1, ap2, direcc, telefono, nombre);
        boolean ins = Model.instance().InsertarUsuario(ingresar);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/Vistas/VistaPrincipal.jsp");
        dispatcher.forward(request, response);
    }

    protected void AgregarPizza(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String nombre = request.getParameter("nombre");
        ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        ArrayList<Ingrediente> listaTem = new ArrayList<>();
        ArrayList<Pizza> listaPP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        int j = 0;
        for (Ingrediente i : listaI) {

            String id = request.getParameter("ingrediente" + j);
            if (id != null) {
                listaTem.add(i);
            }
            j++;
        }
        Pizza pizza = new Pizza(nombre, listaTem, listaPP.size() + 1);
        Model.instance().AgregarPizza(pizza);
        ArrayList<Pizza> listaPizzas = Model.instance().ObtenerListaPizzas();
        request.getSession().setAttribute("listaPizzas", listaPizzas);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/Vistas/Menu.jsp");
        dispatcher.forward(request, response);
    }

    protected void eliminarPizza(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int numC = Integer.parseInt(request.getParameter("PizzaID"));
        ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        for (int i = 0; i < listaP.size(); i++) {
            if (listaP.get(i).getPizzaID() == numC) {
                Model.instance().EliminarPizza(listaP.get(i).getPizzaID());
                listaP.remove(i);
            }
        }
        request.getSession().setAttribute("listaPizzas", listaP);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/Vistas/ModificarPizzas.jsp");
        dispatcher.forward(request, response);
    }

    protected void modificarUsuario(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String id = request.getParameter("id");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String contrasena = request.getParameter("contrasena");

        Usuario usuario;
        usuario = (Usuario) request.getSession(true).getAttribute("Usuario");
        usuario.setClave_acceso(contrasena);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        Model.instance().ModificarUsuario(usuario);
        request.getSession().setAttribute("Usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/Vistas/Menu.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ServletUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ServletUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
