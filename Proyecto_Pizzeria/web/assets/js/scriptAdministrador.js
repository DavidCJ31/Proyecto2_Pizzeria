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
    document.getElementById("inputIdPizza2").setAttribute('value', idPizza);
    document.getElementById("inputNombrePizza").value = nombrePizza;
    document.getElementById("inputTamanoPizza").value = tamanoPizza;
    document.getElementById("inputPrecioPizza").value = precioPizza;
}

var cant = 0;
//var start_date = document.getElementById("start-date").value;
//var end_date = document.getElementById("end-date").value;
//
//var table = document.getElementById("tablaOrdenesEntregadas").tBodies[0];

function ConsultarOrdenesEntregadas() {
    var min = $('#start-date').val();
    var max = $('#end-date').val();
    if (cant === 0) {
        document.getElementById("tablaOrdenesEntregadas").removeAttribute("style");

        // Set up your table
        table = $('#tablaOrdenesEntregadas').DataTable({
            paging: false,
            searching: false,
            //ordering: false,
            info: false
        });
        cant = 1;
// Extend dataTables search
        $.fn.dataTable.ext.search.push(
                function (settings, data, dataIndex) {


                    var createdAt = data[1] || 0; // Our date column in the table

                    if (
                            (min == "" || max == "")
                            ||
                            (moment(createdAt).isSameOrAfter(min) && moment(createdAt).isSameOrBefore(max))
                            )
                    {
                        return true;
                    }
                    return false;
                }
        );
    }
    table.draw();
}