/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

/**
 *
 * @author 50686
 */
public enum IMEC_Usuario {
   // INSERTARFAVORITA("INSERT INTO favorita (cliente_id_cliente, cuenta_num_cuenta) VALUES (?, ?);"),
  //  MODIFICAR("UPDATE cuenta SET saldo_final=? WHERE num_cuenta=?;"),
    //EXCLUIR("DELETE FROM cuenta WHERE  num_cuenta=?;"),
    CONSULTAR("SELECT id, tipo, password, nombre, apellido1, apellido2, direccion, telefono  FROM usuario WHERE password=? AND id=?;"),
   // LISTARCUENTASCLIENTE("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final FROM cuenta WHERE cliente_id_cliente=?; "),
    LISTAR("SELECT id, fecha, estado, usuario FROM orden WHERE usuario=?; "),
    LISTARPIZZA("SELECT ID, nombre, tamano, precio, FROM pizza WHERE ID=?; "),
    CONSULTARPRODUCTO("SELECT ID, nombre, precio, descripcion FROM producto WHERE ID=?; "),
    CONSULTARING("SELECT ID, nombre, precio, FROM ingredientes WHERE ID=?; "),
    LISTARPIZZAORDEN("SELECT orden, pizza, cantidad FROM relacion_pizza_orden WHERE orden=?; "),
    LISTARPRODUCTOORDEN("SELECT orden, cantidad, producto FROM relacion_producto_orden WHERE orden=?; "),
     LISTARINGREDIENTESPIZZA("SELECT pizza, ingrediente FROM relacion_pizza_ingredientes WHERE pizza=?; "),
    INSERTARUSUARIO("INSERT INTO usuario VALUES(?,?,?,?);");

    IMEC_Usuario(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
