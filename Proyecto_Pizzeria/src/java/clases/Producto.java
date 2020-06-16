
package clases;


public class Producto {

    public Producto(int precio, String descripcion, int IDProducto, int cantidadProducto, String nombre) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.IDProducto = IDProducto;
        this.cantidadProducto = cantidadProducto;
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
