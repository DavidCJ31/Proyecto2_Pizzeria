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
    </head>
    <body>



        <%
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
            ArrayList<Orden> listaOrden = (ArrayList<Orden>) request.getSession().getAttribute("listaOrden");
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
                            <td id="<%=c.getIdOrden() + "-idEstadoOrden"%>"><%=c.getEstado()%></td>
                            <td><button type="submit" onclick="CambiarEstado('<%=c.getIdOrden()%>')" class="btn btn-primary">Cambiar Estado</button></td>

                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>