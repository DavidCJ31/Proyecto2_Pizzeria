<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Principal</title>
        <%@include file="/Vistas/Head.jsp"%>  
    </head>

    <body>
        <%@include file="/Vistas/popUpRegistrarUsuario.jsp" %>
        <%response.setHeader("cache-control", "no_cache, no-store, must-revalidate");%>
            
        <div class="row" id="log">
            <div class="col-md-12" >
                <div class="login-dark" id="logBanco" >
                    <form method="post" action="logIn" >
                        <h2 class="sr-only" style=" " >Login Form</h2>
                        <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
                        <div class="form-group"><input class="form-control" type="cedúla" name="cedula" placeholder="Cédula" value="123" /></div>
                        <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Password" value="admin" /></div>
                        <div  class="form-group">
                            <button class="btn btn-warning btn-block" type="submit">Log In</button>
                        </div>
                        <div class="form-group">
                            <button data-toggle="modal"   type="button" data-target="#registrarUser"  class="btn btn-warning btn-block" >Registrarse</button>
                        </div>
                        <div class="dropdown show" id="log" style="margin: 5%">
                            <select class="btn btn-warning dropdown-toggle" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" name="rol">
                                <option class="dropdown-item">Cliente</option>
                                <option class="dropdown-item">Administrador</option>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body> 
</html>
