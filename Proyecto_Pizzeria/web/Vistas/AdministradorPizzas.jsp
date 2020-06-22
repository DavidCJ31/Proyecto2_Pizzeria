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
        <title>Administrar Pizzas</title>		
        <%@include file="/Vistas/Head.jsp"%>    

    </head>
    <body>

        <%
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        %>

        <nav class="navbar justify-content-between">
            <div  class="dropdown show">
                <div id="dropHeder">
                    <button class="btn btn-secondary dropdown-toggle" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Usuario
                    </button>
                    <label id="usuario"> 
                    </label>
                    <div class="dropdown-menu" id="showDrop">
                        <a href="Vistas/Perfil.jsp"><button class="dropdown-item">Perfil</button></a>
                        <form action="Vistas/VistaPrincipal.jsp" >
                            <button type="submit" class="dropdown-item">Salir</button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
        <div id="fondoTabla">
            <div>
                <table class="table table-hover  table-light">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Pizza</th>
                            <th scope="col">Precio</th>
                            <th scope="col" colspan="2">Accion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Pizza c : listaP) {
                        %>

                        <tr>
                            <th scope="row"><%=c.getPizzaID()%></th>
                            <td><%=c.getNombre()%></td>
                            <td><%=c.getPrecio()%></td>
                            <td><button class="btn btn-default"><img src="../assets/imagenes/Compose-512.png"  style=" width: 50px; height: 50px;"></button></td>
                            <td></td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td colspan="2">Larry the Bird</td>
                            <td>@twitter</td>
                        </tr>
                    </tbody>
                </table>

            </div>

        </div>
    </body>
</html> 
