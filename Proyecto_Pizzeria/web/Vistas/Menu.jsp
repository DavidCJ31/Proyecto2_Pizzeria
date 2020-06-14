<%-- 
    Document   : Menu
    Created on : Jun 13, 2020, 5:12:41 PM
    Author     : metal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <%@include file="/Vistas/Head.jsp"%>  
    </head>
    <body>
        <%--
        cliente cli = (cliente) session.getAttribute("cliente");
        ArrayList<Pizza> listaP = (ArrayList) session.getAttribute("listaP");
        --%>
        <div id="fondoTabla">
            <h1 id="TituloVista">Menu</h1>  
            <div id="marg">
                <table class="table table-bordered table-striped mb-0 " id="example" style="">
                    <thead>
                        <tr>
                            <th id="colcorta" scope="col">#</th>
                            <th scope="col">Pizza</th>
                            <th scope="col">Ingredientes</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Agregar</th>
                            <th scope="col">Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--
                            int i = 0;

                            for (Pizza c : listaP) {
                                i++;

                        --%>

                        <tr style="height: 10px">
                            <td> <%--= i--%> 1  </td>
                            <td width="200">pizzaN<%--=c.getNombre()--%></td>
                            <td style="">Ingred<%--=c.getIngredientes().toString()--%></td>
                            <td style="">1200<%--=c.getPrecio()--%></td>
                            <td >
                                <form method="GET" action="">
                                    <input name="" id="Cuenta" style="display:none;" value="<%--=c.getNcuenta()--%>">
                                    <button  type="submit" class="btn btn-default"><img  src="../assets/imagenes/agregar.png"  style=" width: 50px; height: 50px;"></button>
                                </form>
                            </td>
                            <td >
                                <form action="">
                                    <button  type="submit" class="btn btn-default"><%--img  src="../assets/imagenes/Detalle.png"  style=" width: 50px; height: 50px;"--%>></button>
                                    <input style=" display:none;" name="Cuenta" id="Cuenta" value="<%--=c.getNcuenta()--%>">
                                </form>
                            </td>
                            <td width="200">
                                <form action="">
                                    <button type="submit" class="btn btn-default"><%--img  src="../assets/imagenes/trash.png" style=" width: 50px; height: 50px;"--%>></button>
                                    <input style=" display:none;" name="Cuenta" id="Cuenta" value="<%--=c.getNcuenta()--%>"> 
                                </form>
                            </td>
                        </tr>
                        <%--}--%>

                    </tbody>
                </table>
                <button id="marg" type="button" data-toggle="modal" data-target="#Moda" class="btn btn-warning" >Realizar Pedido</button>
            </div>
        </div>
        <div class="modal fade" id="Moda"   tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content"  id="center">
                    <h5 class="modal-title" id="centro">Forma de Pago</h5>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="Pedir" id="PerfilTable">
                        <div id="logBanco" class="input-group-prepend">
                            <div class="input-group-text">
                                <input type="checkbox">
                                <label>Tarjeta</label>
                            </div>
                        </div>
                        <div id="logBanco" class="input-group-prepend">
                            <div class="input-group-text">
                                <input type="checkbox">
                                <label>Contra Entrega</label>
                            </div>
                        </div>
                        <button type="button" class="btn btn-danger"  data-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-warning" >Aceptar</button>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
