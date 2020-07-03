/*
 <%-- 
 
// // EIF209 - Programación 4 – Proyecto #2 
// Junio 2020 
// // Autores: 
//  - 116670651 Steven Sandino Solórzano
//  -  
//  - 
// // --%> 
 */
package coneccion;


public enum IMEC_Usuario {
    EXCLUIRPIZZA("DELETE FROM pizza WHERE  ID=?;"),
    EXCLUIRPIZZAINGREDIENTE("DELETE FROM relacion_pizza_ingredientes WHERE  pizza=?;"),
    CONSULTAR("SELECT id, tipo, password, nombre, apellido1, apellido2, direccion, telefono FROM usuario WHERE password=? AND id=?;"),
    LISTAR("SELECT id, fecha, estado, usuario, formaPago FROM orden WHERE usuario=?; "),
    LISTARPIZZA("SELECT ID, nombre, precio FROM pizza WHERE ID=?; "),
    INSERTARPIZZA("INSERT INTO pizza(ID, nombre, precio, tamano) VALUES(?,?,?,?); "),
    INSERTARPIZZAINGREDIENTE("INSERT INTO relacion_pizza_ingredientes(pizza, ingrediente) VALUES(?,?); "),
    LISTARPIZZAS("SELECT ID, nombre, precio, tamano FROM pizza; "),
    CONSULTARPRODUCTO("SELECT ID, nombre, precio, descripcion FROM producto WHERE ID=?; "),
    LISTARPRODUCTO("SELECT ID, nombre, precio, descripcion FROM producto; "),
    CONSULTARING("SELECT ID, nombre, precio FROM ingredientes WHERE ID=?; "),
    LISTARING("SELECT ID, nombre, precio FROM ingredientes;"),
    LISTARPIZZAORDEN("SELECT orden, pizza, cantidad FROM relacion_pizza_orden WHERE orden=?; "),
    LISTARPRODUCTOORDEN("SELECT orden, cantidad, producto FROM relacion_producto_orden WHERE orden=?; "),
    LISTARINGREDIENTESPIZZA("SELECT pizza, ingrediente FROM relacion_pizza_ingredientes WHERE pizza=?; "),
    LISTARINGREDIENTESADICIONALES("SELECT pizza, ingredientes, orden FROM adicionales WHERE pizza=? AND orden = ?; "),
    INSERTARPIZZAORDEN("INSERT INTO relacion_pizza_orden(orden, pizza, cantidad) VALUES(?,?,?); "),
    INSERTARPRODUCTOORDEN("INSERT INTO relacion_producto_orden(orden, cantidad, producto) VALUES(?,?,?); "),
    INSERTARINGREDIENTESADICIONALES("INSERT INTO adicionales(pizza, ingredientes, orden) VALUES(?,?,?); "),
    INSERTARORDEN("INSERT INTO orden(id, estado, usuario, formaPago,total) VALUES(?,?,?,?,?); "),
    INSERTARUSUARIO("INSERT INTO usuario(id, tipo, password, nombre, apellido1, apellido2, direccion, telefono) VALUES(?,?,?,?,?,?,?,?); "),
    MODIFICARUSUARIO("UPDATE  usuario SET password=?, direccion=?, telefono=? WHERE id=?;"),
    LISTA_ORDENES_EN_PREPARACION("SELECT * FROM orden WHERE estado='En preparacion';"),
    ACTUALIZAR_ORDENES_EN_PREPARACION("UPDATE orden SET estado=? WHERE estado=?;"),
    ULTIMA_ORDEN("SELECT TOP 1 * FROM  orden ORDER BY id DESC;"),
    MODIFICARORDEN("UPDATE  orden SET estado=? WHERE id=?;"),
    AGREGAR_COMENTARIO("INSERT INTO comentarios(usuario, descripcion) VALUES (?,?);"),
    CONSULTAR_COMENTARIOS("SELECT * FROM comentarios;"),
    AGREGAR_INGREDIENTE("INSERT INTO ingredientes(ID, nombre, precio) VALUES (?,?,?);"),
    AGREGAR_PRODUCTO("INSERT INTO producto(ID, nombre, precio, descripcion) VALUES (?,?,?,?);"),
    LISTA_ORDENES_ENTREGADAS("SELECT * FROM orden WHERE estado='Entregado';");
    
    IMEC_Usuario(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
