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
                                            <h2 class="modal-title" id="centro"><%=c.getNombre()%></h2>
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div id="marg">
                                                <h3>Ingredientes: </h3>
                                                <%
                                                    for(Ingrediente ingre: c.getListaIngredientes()){
                                                %>  
                                                <h4><%= ingre.getNombre() %></h4>
                                                <%
                                                    }
                                                %>
                                            </div>
                                            <div id="marg">
                                                <h3>Precio: </h3>
                                                <h4><%=c.getPrecio()%></h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td style=""><%=c.getNombre()%></td>
                            <td>
                                <input name="" id="<%=i + "p"%>" style="display:none;" value="<%=pizzaJson(c, "personal")%>">
                                <button type="submit" onclick="agregarPizzaCarrito('<%=i + "p"%>')" class="btn btn-default">
                                    <img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;">
                                </button>
 
                            </td>
                            <td >
                                <input name="" id="<%=i + "g"%>" style="display:none;" value="<%=pizzaJson(c, "grande")%>">
                                <button  type="submit" onclick="agregarPizzaCarrito('<%=i + "g"%>')" class="btn btn-default"><img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;"></button>
                            </td>
                            <td width="200">
                                 <input name="" id="<%=i + "f"%>" style="display:none;" value="<%=pizzaJson(c, "familiar")%>">
                                <button  type="submit" onclick="agregarPizzaCarrito('<%=i + "f"%>')" class="btn btn-default"><img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;"></button>
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
                                        <h4 id="h4">Ingredientes: </h4>
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
if(tam.equals("personal")){
            r.put("precio", p.getPrecio());
}
if(tam.equals("grande")){
            r.put("precio", p.getPrecio()*2);
}
if(tam.equals("familiar")){
            r.put("precio", p.getPrecio()*3);
}
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
    divCarro = document.getElementById("carritoIconDiv");
    var imagen = document.createElement("img");
    imagen.setAttribute("src", "../assets/imagenes/carrito.png");
    imagen.setAttribute("style", "width: 50px; height: 50px; float: left;");
    var numCarro = document.createElement("h5");
    numCarro.textContent = carritoCompras.length;
     numCarro.setAttribute("id", "numCarro");
     numCarro.setAttribute("style", "float: left;")
    divCarro.appendChild(imagen);
    divCarro.appendChild(numCarro);
    function agregarPizzaCarrito(llave) {
        inp = document.getElementById(llave);
        var pizza = inp.value;
        carritoCompras.push(pizza);
        actualizarNumeroCarrito();
    }
    function actualizarNumeroCarrito(){
         numCarro = document.getElementById("numCarro");
         numCarro.textContent = carritoCompras.length;
    }

</script>
