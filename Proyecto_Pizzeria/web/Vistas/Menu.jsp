<%-- 
    Document   : Menu
    Created on : Jun 13, 2020, 5:12:41 PM
    Author     : metal
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="clases.Ingrediente"%>
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
        <title>Menu</title>		
        <%@include file="/Vistas/Head.jsp"%>  
        <%@include file="/Vistas/Heder.jsp"%>  

    </head>
    <body>
        <%
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
            ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        %>
        <div id="fondoTabla">
            <h1 id="TituloVista">Menu</h1>  
            <div id="marg">
                <table class="table table-bordered table-striped mb-0 " id="example" style="">
                    <thead>
                        <tr>
                            <th id="colcorta" scope="col">#</th>
                            <th scope="col">Pizza</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Personal</th>
                            <th scope="col">Grande</th>
                            <th scope="col">Familiar</th>
                            <th scope="col">Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 0;

                            for (Pizza c : listaP) {
                                i++;

                        %>

                        <tr style="height: 10px">
                            <td> <%= i%></td>
                            <td >
                                <button  type="button" data-toggle="modal" data-target="#Moda<%= i%>" class="btn btn-default"><img  src="../assets/imagenes/PizzaLogo.png"  style=" width: 50px; height: 50px;"></button>
                                <div class="modal fade" id="Moda<%= i%>"   tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content"  id="center">
                                            <h5 class="modal-title" id="centro"><%=c.getNombre()%></h5>
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div>
                                                <h3>Ingredientes: </h3>
                                                <h4><%=c.getListaIngredientes().toString()%></h4>

                                            </div>
                                            <div>
                                                <h3>Precio: </h3>
                                                <h4><%=c.getPrecio()%></h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td style=""><%=c.getNombre()%></td>
                            <td >
                                <input name="" id="<%=i + "p"%>" style="display:none;" value="<%=pizzaJson(c, "personal")%>">
                                <button type="button" onclick="agregarPizzaCarrito('<%=i + "p"%>')" class="btn btn-default">
                                    <img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;">
                                </button>

                            </td>
                            <td >
                                <button  type="submit" class="btn btn-default"><img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;"></button>
                            </td>
                            <td width="200">
                                <button  type="submit" class="btn btn-default"><img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;"></button>
                            </td>
                            <td width="200">
                                <button  type="submit" class="btn btn-default"><img  src="../assets/imagenes/delete.png"  style=" width: 50px; height: 50px;"></button>
                            </td>
                        </tr>
                        <%}%>

                    </tbody>
                </table>
                <button id="marg" type="button" data-toggle="modal" data-target="#Moda" class="btn btn-warning" >Agregar Pizza</button>
            </div>
        </div>
        <div class="modal fade" id="Moda"   tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content"  id="center">
                    <h5 class="modal-title" id="centro">Agregar Pizza</h5>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="CrearPizza" id="PerfilTable">
                        <div class="modal-body jumbotron" id="modBody">
                            <div class="text-center border border-light p-5 " >
                                <div class="form-row mb-4">
                                    <div class="col">
                                        <!-- First name -->

                                        <input type="text" id="defaultRegisterFormFirstName" name="nombre" class="form-control" placeholder="Nombre" required >
                                    </div>
                                </div>
                                <div class="form-row mb-4">
                                    <div class="col">
                                        <h4>Ingredientes: </h4>
                                    </div>
                                </div>
                                <%
                                    int j = 0;

                                    for (Ingrediente k : listaI) {
                                        j++;
                                %>
                                <div class="form-row mb-4">
                                    <div id="logBanco" class="input-group-prepend">
                                        <div class="input-group-text">
                                            <div class="col">
                                                <input  type="checkbox" name="ingrediente<%=j - 1%>">
                                            </div>
                                            <div class="col">
                                                <label id="marg"><%=k.getNombre()%></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                            </div>
                            <!-- Default form register -->
                        </div>
                        <button type="button" class="btn btn-danger"  data-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-warning" >Aceptar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <%!
        public String pizzaJson(Pizza p, String tam) {
            JSONObject r = new JSONObject();
            JSONArray a = new JSONArray();
            r.put("nombre", p.getNombre());
            r.put("tamano", tam);
            r.put("id", p.getPizzaID());
            r.put("precio", p.getPrecio());
            for (Ingrediente ing : p.getListaIngredientes()) {
                JSONObject ingred = new JSONObject();
                ingred.put("id", ing.getIdIng());
                ingred.put("nombre", ing.getNombre());
                ingred.put("precio", ing.getPrecio());
                a.put(ingred);
            }
            r.put("ingredientes", a);
            String aux = r.toString();
            aux = aux.replace('\"', '\'');
            return aux;
        }

    %>
</html> 
<script>
    var carritoCompras = [];

    function agregarPizzaCarrito(llave) {
        inp = document.getElementById(llave);
        var pizza = inp.value;
        carritoCompras.push(pizza);
    }

</script>
