<%--    
// // EIF209 - Programación 4 ? Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  - 207600154 David Cordero Jimenez
//  - 
// // --%> 
<%@page import="clases.Pizza"%>
<%@page import="clases.Ingrediente"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Pizza> listaP = (ArrayList<Pizza>) request.getSession().getAttribute("listaPizzas");
    ArrayList<Ingrediente> listaI = (ArrayList<Ingrediente>) request.getSession().getAttribute("listaIngrediente");
%>
<div class="container container-fluid" id="containerCrearEncuesta">
    <form action="ServletAdministrador" >
        <input type="hidden" name="instruccion" value="ActualizarPizza">
        <div class="modal fade" id="ModificarPizza" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="ModalLabel">ModificarPizza</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body jumbotron" id="modBody">
                        <div class="text-center border border-light p-5 " >
                            <div class="form-row mb-4">
                                <div class="col">
                                    <!-- ID Pizza -->
                                    <label for="inputIdPizza">ID:</label>
                                    <input type="hidden" id="inputIdPizza2" name="inputIdPizza2">
                                    <input type="text" id="inputIdPizza" name="inputIdPizza" class="form-control" disabled >
                                </div>
                                <div class="col">
                                    <!-- Nombre Pizza -->
                                    <label for="inputNombrePizza">Nombre:</label>
                                    <input type="text" id="inputNombrePizza" name="inputNombrePizza" class="form-control" placeholder="Nombre de la pizza" required>
                                </div>
                            </div>
                            <div class="form-row mb-4">
                                <div class="col">
                                    <!-- First name -->
                                    <label for="tamano">Tamaño:</label>
                                    <select id="inputTamanoPizza" name="inputTamanoPizza" class="form-control">
                                        <option value="Personal">Personal</option>
                                        <option value="Grande">Grande</option>
                                        <option value="Familiar">Familiar</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <!-- Last name -->
                                    <label for="inputPrecioPizza">Precio:</label>
                                    <input type="number" id="inputPrecioPizza" class="form-control"  name="inputPrecioPizza" min="1" max="50000" required>
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
                                            <input  type="checkbox" name="ingredienteModificar<%=j - 1%>">
                                        </div>
                                        <div class="col">
                                            <label id="marg"><%=k.getNombre()%></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <div class="modal-footer">

                        <button  class="btn btn-warning" id="ButtonActualizarPizza" type="submit">Actualizar</button>

                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <div id="cargando" style="visibility: hidden" class="spinner-grow text-danger" role="status">
                            <span class="sr-only"></span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </form>
</div>