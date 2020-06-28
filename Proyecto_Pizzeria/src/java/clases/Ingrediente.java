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

public class Ingrediente {

    public Ingrediente(String nombre, int precio, int idIng) {
        this.nombre = nombre;
        this.precio = precio;
        this.idIng = idIng;
    }

    public String getNombre() {
        return nombre;
    }
 @XmlElement(name = "nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }
 @XmlElement(name = "precio")
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdIng() {
        return idIng;
    }
 @XmlElement(name = "idIng")
    public void setIdIng(int idIng) {
        this.idIng = idIng;
    }

    public Ingrediente() {
        this.nombre = "";
        this.precio = 0;
         this.idIng = 0;
    }
     public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("nombre", getNombre());
        r.put("precio", getPrecio());
        r.put("idIng", getIdIng());
        return r;
    }
    
    private String nombre;
    private int precio;
    private int idIng;
}
