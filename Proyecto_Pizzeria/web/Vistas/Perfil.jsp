<%-- 
    Document   : Perfil
    Created on : 18/06/2020, 02:38:50 PM
    Author     : david
--%>

<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="../assets/imagenes/pizza.png" /> 
        <title>Perfil</title>
        <%@include file="/Vistas/Head.jsp"%>
    </head>
    <body>
        <%
            response.setHeader("cache-control", "no_cache, no-store, must-revalidate");
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            
        %>
        <%@include file="/Vistas/Heder.jsp" %>
        <a href="/Vistas/Menu.jsp" style="float: right; margin: 25px;">
            <button  type="submit" class="btn btn-warning">
                Atras
            </button>
        </a>
        <h1 id="perfil">Perfil</h1>
        <div id="container">

            <form id="form" action="ModificarUsuario" >
                <table>
                    <tr>
                        <th>
                            <label> Nombre:</label>
                        </th>
                        <th>
                            <input disabled='true' value="<%= us.getNombre()%>"> 
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <label> Apellidos: </label>
                        </th>
                        <th>
                            <input disabled='true' value="<%= us.getApellido_1() +" "+ us.getApellido_2() %>"> 
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <label> Cedula: </label>
                        </th>
                        <th>
                            <input name="id" style="display:none;" value="<%= us.getId()%>">
                            <input disabled='true' value="<%= us.getId()%>"> 
                        </th>
                    </tr>
                                        <tr>
                        <th>
                            <label> Dirección: </label>
                        </th>
                        <th>
                            <input name="direccion" value="<%= us.getDireccion() %>"> 
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <label> Teléfono: </label>
                        </th>
                        <th>
                            <input name="telefono" value="<%= us.getTelefono()%>"> 
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <label> Rol: </label>
                        </th>
                        <th>
                            <input disabled='true' value="<%= us.getTipo() %>"> 
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <label> Contraseña: </label>
                        </th>
                        <th>
                            <input name="contrasena" value="<%= us.getClave_acceso()%>"> 
                        </th>
                    </tr>
                    <tr>
                        <th colspan="2" style="text-align: center;">
                                <button class="btn btn-warning">Aplicar cambios</button>
                        </th>
                    </tr>
                </table>
            </form>
        </div>
</html>
