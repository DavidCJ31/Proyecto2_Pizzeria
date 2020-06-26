function CambiarEstado(id) {
    var idOrden = document.getElementById(id + "-idOrden").innerText;
    var estadoOrden = document.getElementById(id + "-idEstadoOrden");
    var datos = new FormData();
    if (estadoOrden.innerText === "En preparacion") {
        datos.append("idOrden", idOrden);
        estadoOrden.innerText = "En camino";
    }

    datos.append("instruccion", "ModificarOrden");
    getJSON('ServletAdministrador', datos, procesarRespuesta);
}

function getJSON(url, data, callback) {
    data.getAll("productos");
    fetch(url, {
        method: 'POST',
        body: data
    }).then((result) => {
        return result.json();
    }).then(callback);
}

function procesarRespuesta(datos) {
    console.log(datos);
}