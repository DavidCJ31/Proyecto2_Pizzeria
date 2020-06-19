package clases;

import java.util.ArrayList;


public class Usuario {

    public Usuario(String id, String clave_acceso, String tipo, ArrayList<Orden> listaOrdenes, String apellido_1, String apellido_2, String direccion, String telefono, String nombre) {
        this.id = id;
        this.clave_acceso = clave_acceso;
        this.tipo = tipo;
        this.listaOrdenes = listaOrdenes;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre = nombre;
    }
    public Usuario(String id, String clave_acceso, String direccion, String telefono) {
        this.id = id;
        this.clave_acceso = clave_acceso;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave_acceso() {
        return clave_acceso;
    }

    public void setClave_acceso(String clave_acceso) {
        this.clave_acceso = clave_acceso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(ArrayList<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public String getApellido_1() {
        return apellido_1;
    }

    public void setApellido_1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }

    public String getApellido_2() {
        return apellido_2;
    }

    public void setApellido_2(String apellido_2) {
        this.apellido_2 = apellido_2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    
     public Usuario() {
        this.id = "";
        this.clave_acceso = "";
        this.tipo = "";
        this.listaOrdenes = new ArrayList<>();
        this.apellido_1 = "";
        this.apellido_2 = "";
        this.direccion = "";
        this.telefono = "";
        this.nombre = "";
    }

   
    private String id;
    private String clave_acceso;
    private String tipo;
    private ArrayList<Orden> listaOrdenes;
    private String apellido_1;
    private String apellido_2;
    private String direccion;
    private String telefono;
    private String nombre;
    
}
