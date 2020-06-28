<%-- 
// // EIF209 - Programación 4 ? Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  - 207600154 David Cordero Jimenez 
//  - 
// // --%> 
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
            <form action="Vistas/VistaAdministrador.jsp">
                <button  type="submit" class="btn btn-secondary" >
                    Ordenes
                </button>
            </form>
        </div>
        <div id="hederButton">
            <form action="Vistas/ModificarPizzas.jsp">
                <button  type="submit" class="btn btn-secondary" >
                    Modificar Pizzas
                </button>
            </form>
        </div>
        <div id="hederButton">
            <form action="Vistas/Comentarios.jsp">
                <button  type="submit" class="btn btn-secondary" >
                    Comentarios
                </button>
            </form>
        </div>
    </div>
</nav>