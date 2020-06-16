
package clases;

public class Ingrediente {

    public Ingrediente(String nombre, int precio, int idIng) {
        this.nombre = nombre;
        this.precio = precio;
        this.idIng = idIng;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdIng() {
        return idIng;
    }

    public void setIdIng(int idIng) {
        this.idIng = idIng;
    }

    public Ingrediente() {
        this.nombre = "";
        this.precio = 0;
         this.idIng = 0;
    }
    
    private String nombre;
    private int precio;
    private int idIng;
}
