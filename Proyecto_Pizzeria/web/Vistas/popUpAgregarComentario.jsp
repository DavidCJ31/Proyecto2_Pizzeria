<%-- 
 
// // EIF209 - Programación 4 ? Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  -  
//  - 
// // --%> 
<div class="container container-fluid" id="containerCrearEncuesta">
    <form action="Comentar" method="post" id="FormComentario">
        <div class="modal fade" id="registrarComentario" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="ModalLabel">Agregar Comentario</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body jumbotron" id="modBody">
                        <div class="text-center border border-light p-5 " >
                            <div class="form-row mb-4">
                                <div class="col">
                                    <label for="textComent">Comentario:</label>
                                    <textarea form="FormComentario" name="descripcionComentario"  placeholder="Comentario..." class="form-control" id="textComent" rows="2"></textarea>
                                </div>
                            </div>
                        </div>
                        <!-- Default form register -->
                    </div>
                    <div class="modal-footer">

                        <button  class="btn btn-warning" id="ButtonAgregarComentario" type="submit">Comentar</button>

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
