<%-- 
 
// // EIF209 - Programación 4 – Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  - 207600154 David Cordero Jimenez
//  - 
// // --%> 
<%@page import="clases.Orden"%>
<%@page import="clases.Pizza"%>
<%@page import="java.util.ArrayList"%>
<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="../assets/imagenes/pizza.png" /> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>		
        <%@include file="/Vistas/Head.jsp"%>    
        <script src="../assets/js/scriptAdministrador.js" type="text/javascript"></script>
        <%@include file="menuAdministrador.jsp"%>  
        <link href="../assets/css/cssAdministrador.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>



        <%
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            ArrayList<Orden> listaOrden = (ArrayList<Orden>) request.getSession().getAttribute("listaOrden");
            ArrayList<Orden> listaOrdenEntregadas = (ArrayList<Orden>) request.getSession().getAttribute("listaOrdenEntregadas");
        %>

        <div id="fondoTabla">
            <div>
                <table class="table table-hover  table-light">
                    <thead>
                        <tr>
                            <th scope="col">ID Orden</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Estado</th>
                        </tr>
                    </thead>
                    <tbody class="tablaOrdenes">
                        <%
                            for (Orden c : listaOrden) {
                        %>

                        <tr>
                            <th scope="row" id="<%=c.getIdOrden() + "-idOrden"%>"><%=c.getIdOrden()%></th>
                            <td id="<%=c.getIdOrden() + "-fechaOrden"%>"><%=c.getFecha()%></td>
                            <td id="<%=c.getIdOrden() + "-TotalOrden"%>"><%=c.getTotal()%></td>
                            <td id="<%=c.getIdOrden() + "-idEstadoOrden"%>"><%=c.getEstado()%></td>
                            <td><button type="submit" onclick="CambiarEstado('<%=c.getIdOrden()%>')" class="btn btn-primary">Cambiar Estado</button></td>

                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
            <div class="container-consultaOrdenes">
                <h3 style="text-align: center">Consula de Ordenes</h3>
                <%
                    if (listaOrdenEntregadas.size() == 0) {
                        out.println("<h4>No hay ordenes Entregadas</h4>");
                    } else {
                %>

                <div class="form-row mb-4">
                    <div class="input-group input-daterange">
                        <div class="col">
                            <label for="start">Desde</label>
                            <input type="date" id="start-date" name="start-date" class="date-range-filter">
                        </div>
                        <div class="col">
                            <label for="start">Hasta:</label>
                            <input type="date" id="end-date" name="end-date" class="date-range-filter">
                        </div>
                        <div class="col">
                            <button type="button" onclick="ConsultarOrdenesEntregadas()" class="btn btn-info">Ver Pedidos</button>
                        </div>
                    </div>
                </div>
                <table id="tablaOrdenesEntregadas" class="table table-light" style="display: none">
                    <thead>
                        <tr>
                            <th scope="col">ID Orden</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Total</th>
                            <th scope="col">Estado</th>
                        </tr>
                    </thead>
                    <tbody class="tablaOrdenes">
                        <%
                            for (Orden c : listaOrdenEntregadas) {
                        %>

                        <tr>
                            <th scope="row"><%=c.getIdOrden()%></th>
                            <td><%=c.getFecha()%></td>
                            <td><%=c.getTotal()%></td>
                            <td><%=c.getEstado()%></td>

                        </tr>
                        <%}
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>