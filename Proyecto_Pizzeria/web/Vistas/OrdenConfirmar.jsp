<%-- 
    Document   : OrdenConfirmar
    Created on : Jun 18, 2020, 5:26:41 PM
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
        <<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="../assets/imagenes/pizza.png" /> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orden</title>		
        <%@include file="/Vistas/Head.jsp"%>  
        <%@include file="/Vistas/Heder.jsp"%>  
    </head>        <%
        Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
        ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        String carrito = request.getParameter("carroInput");
    %>
    <script>
        function cargarCarrito(carrito) {
            carrito.forEach(
                    function (item) {
                        carritoCompras.push(item);
                    }
            );
            numCarro.textContent = carritoCompras.length;
            actualizarTablaOrden();
        }
    </script>
    <body onload="cargarCarrito(<%=carrito%>)">

        <div id="fondoTabla" >
            <h1 id="TituloVista">Orden</h1>  
            <div id="marg">
                <table class="table table-bordered table-striped mb-0 " id="example" style="">
                    <thead>
                        <tr>
                            <th id="colcorta" scope="col">#</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Adicionales</th>
                            <th scope="col">Precio</th>
                        </tr>
                    </thead>
                    <tbody id="tablaO">
                        <tr style="height: 10px">

                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal fade" id="Moda"   tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content"  id="center">
                    <h3 class="modal-title" id="centro">Agregar Adicional</h3>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div action="" id="PerfilTable">
                        <div class="modal-body jumbotron" id="modBody">
                            <div class="text-center border border-light p-5 " >
                                <div class="form-row mb-4">
                                    <div class="col">
                                        <!-- First name -->

                                        <h4 id="nombre" ></h4>
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
                                                <input  type="checkbox" id="ingrediente<%=j - 1%>" name="ingrediente<%=j - 1%>">
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
                        <input name="PizzaIngredientes" id="PizzaIngredientes" style="display:none;" value="<%=pizzaJson(listaI)%>">
                        <input name="PizzaAdicional" id="PizzaAdicional" style="display:none;" >
                        <button type="button" class="btn btn-danger"  data-dismiss="modal">Cerrar</button>
                        <button type="button" onclick="agregarAdicionalesPizza(<%=pizzaJson(listaI)%>)" data-dismiss="modal" class="btn btn-warning" >Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%!
        public String pizzaJson(ArrayList<Ingrediente> lista) {
            JSONArray a = new JSONArray();
            for (Ingrediente ing : lista) {
                JSONObject ingred = new JSONObject();
                ingred.put("id", ing.getIdIng());
                ingred.put("nombre", ing.getNombre());
                ingred.put("precio", ing.getPrecio());
                a.put(ingred);
            }
            String aux = a.toString();
            aux = aux.replace('\"', '\'');
            return aux;
        }

    %>
</html>

<script>
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
    function seleccionarPizzaAdicionales(pos) {
        pizza = document.getElementById("PizzaAdicional");
        pizza.value = pos;
        h3Nombre = document.getElementById("nombre");
        h3Nombre.textContent = carritoCompras[pos].valueOf()['nombre'];
    }
    function agregarAdicionalesPizza(listaIngredientes) {
        posPizza = document.getElementById("PizzaAdicional").value;
        pizza = carritoCompras[posPizza];
        listaIngredientes.forEach(
                function (item, i) {
                    inputIngrediente = document.getElementById("ingrediente" + i).checked;
                    if (inputIngrediente === true) {
                        pizza.valueOf()['ingredientes'].push(item);
                        pizza.valueOf()['precio'] = pizza.valueOf()['precio'] + item.valueOf()['precio'];
                    }
                }
        );
        actualizarTablaOrden();
    }
    function actualizarTablaOrden() {
        tabla = document.getElementById("tablaO");
        if(tabla.hasChildNodes()){
            var e = tabla.lastElementChild;
            while(e){
                tabla.removeChild(e);
                e = tabla.lastElementChild;
            }
        }
        carritoCompras.forEach(
                function (item, i) {
                    var fila = document.createElement("tr");
                    fila.setAttribute("style", "height: 10px");
                    var colum1 = document.createElement("td");
                    colum1.textContent = i + 1;
                    fila.appendChild(colum1);
                    var colum2 = document.createElement("td");
                    colum2.textContent = item.nombre;
                    fila.appendChild(colum2);
                    var colum3 = document.createElement("td");
                    var boton = document.createElement("button");
                    boton.setAttribute("data-toggle", "modal");
                    boton.setAttribute("type", "button");
                    boton.setAttribute("data-target", "#Moda");
                    boton.setAttribute("class", "btn btn-warning btn-block");
                    boton.setAttribute("onclick", "seleccionarPizzaAdicionales(" + i + ")");
                    boton.textContent = "Agregar Adicionales";
                    colum3.appendChild(boton);
                    fila.appendChild(colum3);
                    var colum4 = document.createElement("td");
                    colum4.textContent = item.precio;
                    fila.appendChild(colum4);
                    tabla.appendChild(fila);
                }
        );
    }

</script>
