
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <div id="hederButton">
            <form action="Vistas/Menu.jsp">
                <button  type="submit" class="btn btn-primary" >
                    Menu
                </button>
            </form>
        </div>
       
        <div id="carritoIconDiv">
            
        </div>


        <!--                <div id="hederButton">
                    <form action="Vistas/VistaCajero.jsp">
                        <button  type="submit" class="btn btn-primary" >
                        Cajero
                    </button>
                    </form>
                </div>
        -->

    </div>
</nav>
<script>
      var carritoCompras = [];
    </script>