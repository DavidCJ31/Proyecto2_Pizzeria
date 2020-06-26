function CambiarEstado(id) {
    var idOrden = document.getElementById(id + "-idOrden").innerText;
    var estadoOrden = document.getElementById(id + "-idEstadoOrden");
    var datos = new FormData();
    if (estadoOrden.innerText === "En Preparacion") {
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

function formatoMoneda(valor) {

    // https://www.fileformat.info/info/unicode/char/20a1/index.htm
    // 20A1(16) = 8353(10)

    return String.fromCharCode(8353) + numeral(valor).format(" 0,0.00");
}

function modificarPizza(IdPizza) {
    var idPizza = document.getElementById(IdPizza + "-idPizza").innerText;
    var nombrePizza = document.getElementById(IdPizza + "-nombrePizza").innerText;
    var tamanoPizza = document.getElementById(IdPizza + "-tamanoPizza").innerText;
    var precioPizza = document.getElementById(IdPizza + "-precioPizza").innerText;

    document.getElementById("inputIdPizza").value = idPizza;
    document.getElementById("inputIdPizza2").setAttribute('value',idPizza);
    document.getElementById("inputNombrePizza").value = nombrePizza;
    document.getElementById("inputTamanoPizza").value = tamanoPizza;
    document.getElementById("inputPrecioPizza").value = precioPizza;
}