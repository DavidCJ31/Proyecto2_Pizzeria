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
package clases;

import javax.xml.bind.annotation.XmlElement;
import org.json.JSONObject;


public class Producto {

    public Producto(int precio, String descripcion, int IDProducto, int cantidadProducto, String nombre) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.IDProducto = IDProducto;
        this.cantidadProducto = cantidadProducto;
        this.nombre = nombre;
    }
    
    public Producto(int precio, String descripcion, int IDProducto, String nombre) {
        
        this.precio = precio;
        this.descripcion = descripcion;
        this.IDProducto = IDProducto;
        this.cantidadProducto = 0;
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }
@XmlElement(name = "precio")
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
@XmlElement(name = "descripcion")
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIDProducto() {
        return IDProducto;
    }
@XmlElement(name = "IDProducto")
    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }
@XmlElement(name = "cantidadProducto")
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getNombre() {
        return nombre;
    }
@XmlElement(name = "nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("precio", getPrecio());
        r.put("descripcion", getPrecio());
        r.put("IDProducto", getIDProducto());
         r.put("cantidadProducto", getCantidadProducto());
          r.put("nombre", getNombre());
        return r;
    }
    
     public Producto() {
        this.precio = 0;
        this.descripcion = "";
        this.IDProducto = 0;
        this.cantidadProducto = 0;
        this.nombre = "";
    }
    
    private int precio;
    private String descripcion;
    private int IDProducto;
    private int cantidadProducto;
    private String nombre;
    
}
