/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import org.json.JSONObject;


public class Pizza {

    public Pizza(String nombre, int precio, ArrayList<Ingrediente> listaIngredientes, int cantidad, int pizzaID) {
        this.nombre = nombre;
        this.tamanno = "";
        this.precio = precio;
        this.listaIngredientes = listaIngredientes;
        this.cantidad = cantidad;
        this.pizzaID = pizzaID;
    }
    public Pizza(String nombre, String tam,  int precio, int pizzaID) {
        
        this.nombre = nombre;
        this.tamanno = tam;
        this.precio = precio;
        this.listaIngredientes = new ArrayList<>();
        this.cantidad = 0;
        this.pizzaID = pizzaID;
    }
    
    public Pizza(String nombre,  ArrayList<Ingrediente> listaIngredientes, int pizzaID) {
        this.nombre = nombre;
        this.tamanno = "";
        this.precio = calculaPrecio(listaIngredientes);
        this.listaIngredientes = listaIngredientes;
        this.cantidad = 0;
        this.pizzaID = pizzaID;
    }

    public String getNombre() {
        return nombre;
    }
    
    private int calculaPrecio(ArrayList<Ingrediente> listaIngredientes){
        int p = 0;
        for(int i = 0; i<listaIngredientes.size(); i++){
            p = p + listaIngredientes.get(i).getPrecio();
        }
        return p;
    }
 @XmlElement(name = "nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamanno() {
        return tamanno;
    }
 @XmlElement(name = "tamanno")
    public void setTamanno(String tamanno) {
        this.tamanno = tamanno;
    }

    public int getPrecio() {
        return precio;
    }
 @XmlElement(name = "precio")
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }
 @XmlElement(name = "listaIngredientes")
    public void setListaIngredientes(ArrayList<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public int getCantidad() {
        return cantidad;
    }
 @XmlElement(name = "cantidad")
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPizzaID() {
        return pizzaID;
    }
 @XmlElement(name = "pizzaID")
    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
    }
 public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("nombre", getNombre());
        r.put("tamanno", getTamanno());
        r.put("precio", getPrecio());
         r.put("listaIngredientes", getListaIngredientes());
           r.put("cantidad", getCantidad());
            r.put("pizzaID", getPizzaID());
        return r;
    }
   
    
    public Pizza() {
         this.nombre = "";
        this.tamanno = "";
        this.precio = 0;
        this.listaIngredientes = new ArrayList<>();
        this.cantidad = 0;
        this.pizzaID = 0;
    }

    
    private String nombre;
    private String tamanno;
    private int precio;
    private ArrayList<Ingrediente> listaIngredientes;
    private int cantidad;
    private int pizzaID;
}
