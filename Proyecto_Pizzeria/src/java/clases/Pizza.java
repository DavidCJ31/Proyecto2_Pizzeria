/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;


public class Pizza {

    public Pizza(String nombre, String tamanno, int precio, ArrayList<Ingrediente> listaIngredientes, int cantidad, int pizzaID) {
        this.nombre = nombre;
        this.tamanno = tamanno;
        this.precio = precio;
        this.listaIngredientes = listaIngredientes;
        this.cantidad = cantidad;
        this.pizzaID = pizzaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamanno() {
        return tamanno;
    }

    public void setTamanno(String tamanno) {
        this.tamanno = tamanno;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(ArrayList<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
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
