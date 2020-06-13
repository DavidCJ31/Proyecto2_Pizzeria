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
    INSERTARMOVIMIENTO("INSERT INTO movimiento (id_movimiento, cuenta_num_cuenta, monto, movimientocol) VALUES (?, ?, ?, ?)"),
    INSERTARTRANSFERENCIA("INSERT INTO transferencia (id_transferencia, cuenta_origen, cuenta_destino, aplicado) VALUES (?, ?, ?, ?);"),
    INSERTARFAVORITA("INSERT INTO favorita (cliente_id_cliente, cuenta_num_cuenta) VALUES (?, ?);"),
    MODIFICAR("UPDATE cuenta SET saldo_final=? WHERE num_cuenta=?;"),
    MODIFICARMOVIMIENTO("UPDATE movimiento SET aplicado=? WHERE id_movimiento=?;"),
    EXCLUIR ("DELETE FROM cuenta WHERE  num_cuenta=?;"),
    EXCLUIRFAVORITA("DELETE FROM favorita WHERE  cuenta_num_cuenta=?;"),
    EXCLUIRMOVIMIENTOS("DELETE FROM movimiento WHERE  cuenta_num_cuenta=?;"),
    EXCLUIRTRANSFERENCIAS("DELETE FROM transferencia WHERE  cuenta_origen or cuenta_destino=?;"),
    CONSULTARCLIENTE("SELECT id_cliente, usuario_id_usuario, apellidos, nombre, telefono FROM cliente WHERE id_cliente=?;"),
    CONSULTAR("SELECT id_usuario, clave_acceso, clave_vencida, rol FROM usuario WHERE clave_acceso=?;"),
    CONSULTARCUENTA("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final FROM cuenta WHERE num_cuenta=?;"),
    CONSULTARTIPOCUENTA("SELECT id_tipo_cuenta, descripción, tasa_interés FROM tipo_cuenta WHERE id_tipo_cuenta=?;"),
    CONSULTARMONEDA("SELECT nombre, descripcion, simbolo, tipo_cambio_compra, tipo_cambio_venta FROM moneda WHERE nombre=?;"),
    LISTARMOVIMIENTO("SELECT id_movimiento, cuenta_num_cuenta, monto, fecha, aplicado, movimientocol FROM movimiento WHERE cuenta_num_cuenta=?;"),
    LISTARTRANSFERENCIA("SELECT id_transferencia, cuenta_origen, cuenta_destino, monto, fecha, aplicado FROM transferencia WHERE cuenta_origen=?;"),
    LISTARFAVORITAS("SELECT cliente_id_cliente, cuenta_num_cuenta FROM favorita WHERE cliente_id_cliente=?;"),
    LISTARCLIENTES("SELECT id_cliente, usuario_id_usuario, apellidos, nombre, telefono FROM cliente;"),
    LISTARCUENTASCLIENTE("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final FROM cuenta WHERE cliente_id_cliente=?; "),
    LISTAR("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final FROM cuenta; "),
    INSERTARUSUARIO("INSERT INTO usuario VALUES(?,?,?,?);"),
    INSERTARCLIENTE("INSERT INTO cliente VALUES(?,?,?,?,?);"),
    INSERTARCUENTA("INSERT INTO cuenta VALUES(?,?,?,?,NOW(),?,?,?,NOW(),?);"),
    INTERESES("UPDATE cuenta SET saldo_final = saldo_final + ? WHERE num_cuenta = ?;");
    IMEC_Usuario(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
