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
package Servicios;

import Modelo.Model;
import clases.Ingrediente;
import clases.Pizza;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author mario
 */
@MultipartConfig
@WebServlet(name = "ServletAdministrador", urlPatterns = {"/ServletAdministrador"})
public class ServletAdministrador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if ("ModificarOrden".equals(request.getParameter("instruccion"))) {
            this.cambiarEstado(request, response);
        }
        if ("CrearPizza".equals(request.getParameter("instruccion"))) {
            this.AgregarPizza(request, response);
        }
        if ("EliminarPizza".equals(request.getParameter("instruccion"))) {
            this.eliminarPizza(request, response);
        }
        if ("ActualizarPizza".equals(request.getParameter("instruccion"))) {
            this.actualizarPizza(request, response);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    protected void cambiarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idS = request.getParameter("idOrden");
        int id = Integer.parseInt(idS);
        try (PrintWriter out = response.getWriter()) {

            JSONObject r = new JSONObject();
            boolean estado = Model.instance().ModificarOrdenACamino(id);
            if (estado == true) {
                r.put("Estado", "true");
            } else {
                r.put("Estado", "false");
            }
            out.println(r.toString(4));
        }
    }

    protected void AgregarPizza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int PizzaId = Integer.parseInt(request.getParameter("PizzaID"));
        Pizza pizza = new Pizza(nombre, listaTem, PizzaId);
        pizza.setTamanno(request.getParameter("tamano"));
        Model.instance().AgregarPizza(pizza);
        ArrayList<Pizza> listaPizzas = Model.instance().ObtenerListaPizzas();
        request.getSession().setAttribute("listaPizzas", listaPizzas);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/Vistas/ModificarPizzas.jsp");
        dispatcher.forward(request, response);
    }

    protected void eliminarPizza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void actualizarPizza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int PizzaId = Integer.parseInt(request.getParameter("inputIdPizza2"));
        ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        for (int i = 0; i < listaP.size(); i++) {
            if (listaP.get(i).getPizzaID() == PizzaId) {
                Model.instance().EliminarPizza(listaP.get(i).getPizzaID());
                listaP.remove(i);
            }
        }
        String nombre = request.getParameter("inputNombrePizza");
        ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        ArrayList<Ingrediente> listaTem = new ArrayList<>();
        int j = 0;
        for (Ingrediente i : listaI) {

            String id = request.getParameter("ingredienteModificar" + j);
            if (id != null) {
                listaTem.add(i);
            }
            j++;
        }
        Pizza pizza = new Pizza(nombre, listaTem, PizzaId);
        pizza.setTamanno(request.getParameter("inputTamanoPizza"));
        pizza.setPrecio(Integer.parseInt(request.getParameter("inputPrecioPizza")));
        Model.instance().AgregarPizza(pizza);
        ArrayList<Pizza> listaPizzas = Model.instance().ObtenerListaPizzas();
        request.getSession().setAttribute("listaPizzas", listaPizzas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/ModificarPizzas.jsp");
        dispatcher.forward(request, response);
    }

}
