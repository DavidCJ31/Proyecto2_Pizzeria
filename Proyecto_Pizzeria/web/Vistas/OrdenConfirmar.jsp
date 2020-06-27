<%-- 
    Document   : OrdenConfirmar
    Created on : Jun 18, 2020, 5:26:41 PM
    Author     : metal
--%>

<%@page import="clases.Producto"%>
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
        <%@include file="/Vistas/popUpAgregarComentario.jsp"%>
    </head>        <%
        Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
        ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
        ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        String carrito = request.getParameter("carroInput");
        ArrayList<Producto> listProduct = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
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
        <div class="form-group">
            <button data-toggle="modal" style=" width: min-content;"  type="button" data-target="#Moda2"  class="btn btn-warning btn-block" >Confirmar Orden</button>
        </div>

        <div class="modal fade" id="Moda2"   tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content"  id="center">
                    <h3 class="modal-title" id="centro">Agregar Productos Adicional</h3>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="Vistas/Menu.jsp">
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
                                            <h4 id="h4">Productos Adicionales: </h4>
                                        </div>
                                    </div>
                                    <%
                                        int m = 0;

                                        for (Producto pt : listProduct) {
                                            m++;
                                    %>
                                    <div class="form-row mb-4">
                                        <div id="logBanco" class="input-group-prepend">
                                            <div class="input-group-text">
                                                <div class="col">
                                                    <label id="marg"><%=pt.getNombre()%></label>
                                                </div>
                                                <div class="col">
                                                    <input name="producto<%=m - 1%>" id="producto<%=m - 1%>" style="display:none;" value="<%=productoJson(pt)%>">
                                                    <button  type="button" onclick="agregarProductoCarrito(<%=productoJson(pt)%>)" class="btn btn-default"><img  src="../assets/imagenes/add.png"  style=" width: 50px; height: 50px;"></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                    <div class="form-row mb-4">
                                        <div class="col">
                                            <h4 id="h4">Forma de Pago: </h4>
                                        </div>
                                    </div>
                                    <div class="form-row mb-4">
                                        <div id="logBanco" class="input-group-prepend">
                                            <div class="input-group-text">
                                                <div class="col">
                                                    <input  type="checkbox" name="pagoEfectivo" id="efectivo" onclick="checkPago()" >
                                                </div>
                                                <div class="col">
                                                    <label id="marg">Pago en Efectivo</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row mb-4">
                                        <div id="logBanco" class="input-group-prepend">
                                            <div class="input-group-text">
                                                <div class="col">
                                                    <input  type="checkbox" name="pagoTarjeta" id="tarjeta" onclick="checkPago()">
                                                </div>
                                                <div class="col">
                                                    <label id="marg">Pago con Trajeta</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Default form register -->
                            </div>
                            <input name="CarritoPizzas" id="CarritoPizzas" style="display:none;">
                            <input name="CarritoProductos" id="CarritoProductos" style="display:none;" >
                            <button type="button" class="btn btn-danger"  data-dismiss="modal">Cerrar</button>
                            <button type="button" onclick="guardaOrden()" class="btn btn-warning">Aceptar</button>
                        </div>
                    </form>
                </div>
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

        public String productoJson(Producto ing) {
            JSONObject produc = new JSONObject();
            produc.put("precio", ing.getPrecio());
            produc.put("descripcion", ing.getDescripcion());
            produc.put("id", ing.getIDProducto());
            produc.put("nombre", ing.getNombre());
            String aux = produc.toString();
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

    function guardaOrden() {
        var dato = new FormData();
        carritoCompras.forEach(
                function (item, i) {
                    a = 'pizza' + i;
                    b = JSON.stringify(item);
                    dato.append(a, b);
                    var ing = item.valueOf()['ingredientes']
                    ing.forEach(
                            function (ingred, j) {
                                c = 'ingrediente' + j + 'pizza' + i;
                                d = JSON.stringify(ingred);
                                dato.append(c, d);
                            }
                    );
                }
        );
        carritoProductos.forEach(
                function (prodI, k) {
                    e = 'producto' + k;
                    f = JSON.stringify(prodI);
                    dato.append(e, f);
                }
        );
        if (carritoCompras.length > 0 && carritoProductos.length > 0) {
            getJSON('insertarOrden', dato);
        }
    }

    function getJSON(url, data) {
        fetch(url, {
            method: 'POST',
            body: data
        }).then(limpiaC());
    }

    function limpiaC() {
        for (var i = 0; i <= carritoProductos.length; i++) {
            carritoProductos.pop()
        }
        for (var i = 0; i <= carritoCompras.length; i++) {
            carritoCompras.pop()
        }
        actualizarTablaOrden();
        actualizarNumeroCarrito();
        document.forms[0].action = "Vistas/Menu.jsp";
        document.forms[0].method = "post"; // "get"
        document.forms[0].submit();
    }

    function salvarCarrito() {
        inpPizz = document.getElementById("CarritoPizzas");
        inpProd = document.getElementById("CarritoProductos");
        var inp = "";
        carritoCompras.forEach(
                function (item, i) {
                    inp = inp + JSON.stringify(item.valueOf());
                    if (i + 1 < carritoCompras.length) {
                        inp = inp + ", ";
                    }
                }
        );
        inpPizz.value = inp;
        inp = "";
        carritoProductos.forEach(
                function (item, i) {
                    inp = inp + JSON.stringify(item.valueOf());
                    if (i + 1 < carritoProductos.length) {
                        inp = inp + ", ";
                    }
                }
        );
        inpProd.value = inp;
    }
    function actualizarNumeroCarrito() {
        numC = document.getElementById("numCarro");
        numC.textContent = carritoCompras.length + carritoProductos.length;
    }
    function agregarProductoCarrito(producto) {
        carritoProductos.push(producto);
        actualizarNumeroCarrito();
    }
    function actualizarTablaOrden() {
        tabla = document.getElementById("tablaO");
        if (tabla.hasChildNodes()) {
            var e = tabla.lastElementChild;
            while (e) {
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
                    boton.setAttribute("style", "width: min-content;");
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

    function checkPago() {
        checkEfectivo = document.getElementById("efectivo").checked;
        checkTarjeta = document.getElementById("tarjeta").checked;
        if (checkEfectivo === true) {
            document.getElementById("tarjeta").checked = false;
        }
        if (checkTarjeta === true) {
            document.getElementById("efectivo").checked = false;
        }

    }
</script>
