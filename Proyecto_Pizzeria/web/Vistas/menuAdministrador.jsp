
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
            <form action="Vistas/ModificarPizzas.jsp">
                <button  type="submit" class="btn btn-secondary" >
                    Modificar Pizzas
                </button>
            </form>
        </div>
        <div id="hederButton">
            <form action="#">
                <button  type="submit" class="btn btn-secondary" >
                    Comentarios
                </button>
            </form>
        </div>
    </div>
</nav>