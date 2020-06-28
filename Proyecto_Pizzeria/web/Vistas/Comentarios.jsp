<%-- 
 
// // EIF209 - Programación 4 – Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  - 207600154 David Cordero Jimenez
//  - 
// // --%> 



<%@page import="clases.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="../assets/imagenes/pizza.png" /> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios</title>		
        <%@include file="/Vistas/Head.jsp"%>    
        <script src="../assets/js/scriptAdministrador.js" type="text/javascript"></script>
        <%@include file="menuAdministrador.jsp"%>   
    </head>
    <body>



        <%
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) request.getSession().getAttribute("listaComentarios");
        %>

        <div id="fondoTabla">
            <div>
                <table class="table table-hover  table-light">
                    <thead>
                        <tr>
                            <th scope="col">Usuario</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Comentario</th>
                        </tr>
                    </thead>
                    <tbody class="tablaOrdenes">
                        <%
                            for (Comentario c : listaComentarios) {
                        %>

                        <tr>
                            <th scope="row" id="<%=c.getIdUsuario() + "-idUsuario"%>"><%=c.getIdUsuario()%></th>
                            <td id="<%=c.getIdUsuario() + "-fechaComentario"%>"><%=c.getFecha()%></td>
                            <td id="<%=c.getIdUsuario() + "-descripcionComentario"%>"><%=c.getDescripcion()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>