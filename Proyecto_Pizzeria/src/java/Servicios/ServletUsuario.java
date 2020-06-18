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
import clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author metal
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/logIn", "/Regisistrar", "/CrearPizza"})
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
            ArrayList<Ingrediente> listaIngredientes = Model.instance().ObtenerListaIngredientes();
            request.getSession().setAttribute("listaIngrediente", listaIngredientes);
            if (rol.equals("Cliente")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(
                        "/Vistas/Menu.jsp");
                dispatcher.forward(request, response);
            }
        }else{
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
            ArrayList<Pizza> listaPizzas = Model.instance().ObtenerListaPizzas();
        ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        ArrayList<Ingrediente> listaTem = new ArrayList<>();
        int j = 0;
        for (Ingrediente i : listaI) {

             String id = request.getParameter("ingrediente" + j);
            if (id != null) {
                listaTem.add(i);
            }
            j++;
        }
        Pizza pizza = new Pizza(nombre, listaTem, listaPizzas.size() + 1);
        Model.instance().AgregarPizza(pizza);
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
            Logger.getLogger(ServletUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
