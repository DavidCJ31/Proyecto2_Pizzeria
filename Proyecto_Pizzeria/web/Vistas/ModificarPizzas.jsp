<%@page import="clases.Ingrediente"%>
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
        <title>Modificar Pizzas</title>		
        <%@include file="/Vistas/Head.jsp"%>    
        <script src="../assets/js/scriptAdministrador.js" type="text/javascript"></script>
        <%@include file="menuAdministrador.jsp"%>   
        <%@include file="popUpModificarPizza.jsp"%>
    </head>
    <body>



        <%
            Usuario us = (Usuario) request.getSession().getAttribute("Usuario");
            //ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
            //ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
        %>

        <div id="fondoTabla">
            <h1 id="TituloVista">Menu</h1>  
            <div id="marg">
                <table class="table table-bordered table-striped mb-0 " id="example" style="">
                    <thead>
                        <tr>
                            <th id="colcorta" scope="col">Id</th>
                            <th scope="col">Pizza</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Tamaño</th>
                            <th scope="col">Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Pizza c : listaP) {
                        %>

                        <tr style="height: 10px">
                            <td id="<%=c.getPizzaID() + "-idPizza"%>"> <%=c.getPizzaID()%></td>
                            <td >
                                <button  type="button" data-toggle="modal" data-target="#Moda<%= c.getPizzaID()%>" class="btn btn-default"><img  src="../assets/imagenes/PizzaLogo.png"  style=" width: 50px; height: 50px;"></button>
                                <div class="modal fade" id="Moda<%= c.getPizzaID()%>"   tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
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
                                                    for (Ingrediente ingre : c.getListaIngredientes()) {
                                                %>  
                                                <h4><%= ingre.getNombre()%></h4>
                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td id="<%=c.getPizzaID() + "-nombrePizza"%>"><%=c.getNombre()%></td>
                            <td id="<%=c.getPizzaID() + "-tamanoPizza"%>"><%=c.getTamanno()%></td>
                            <td id="<%=c.getPizzaID() + "-precioPizza"%>"><%=c.getPrecio()%></td>
                            <td width="200">
                                <form action="ServletAdministrador" >
                                    <input type="hidden" name="instruccion" value="EliminarPizza">
                                    <input name="PizzaID" id="PizzaID" style="display:none;" value="<%=c.getPizzaID()%>">
                                    <button  type="submit" class="btn btn-default"><img  src="../assets/imagenes/delete.png"  style=" width: 50px; height: 50px;"></button>
                                </form>
                            </td>
                            <td width="200">
                                <button id="buttonModificar" type="button" data-toggle="modal" data-target="#ModificarPizza" class="btn btn-default" onclick="modificarPizza('<%= c.getPizzaID()%>')"><img  src="../assets/imagenes/modificarPizza.png"  style=" width: 50px; height: 50px;"></button>
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
                    <form action="ServletAdministrador" id="PerfilTable">
                        <input type="hidden" name="instruccion" value="CrearPizza">
                        <input type="hidden" name="PizzaID" value="<%=campoVacio(listaP)%>">
                        <div class="modal-body jumbotron" id="modBody">
                            <div class="text-center border border-light p-5 " >
                                <div class="form-row mb-4">
                                    <div class="col">
                                        <input type="text" id="defaultRegisterFormFirstName" name="nombre" class="form-control" placeholder="Nombre" required >
                                    </div>
                                </div>
                                <div class="form-row mb-4">
                                    <div class="col">
                                        <label for="tamano">Tamaño:</label>
                                        <select name="tamano" id="tamano">
                                            <option value="Personal">Personal</option>
                                            <option value="Grande">Grande</option>
                                            <option value="Familiar">Familiar</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row mb-4">
                                    <div class="col">
                                        <h4 id="h4">Ingredientes: </h4>
                                    </div>
                                </div>
                                <%
                                    j = 0;

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
</html>

<%!
    public int campoVacio(ArrayList<Pizza> listaP) {
        int campoVacio = 0;
        if (listaP.size() == 0) {
            return 0;
        } else {
            for (Pizza p : listaP) {
                if (p.getPizzaID() == campoVacio) {
                    campoVacio++;
                } else {
                    return campoVacio;
                }
            }
        }
        return campoVacio;
    }

%>