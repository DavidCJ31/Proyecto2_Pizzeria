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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import org.json.JSONObject;

public class Orden {

    public Orden(String fPago, String estado, int idOrden, ArrayList<Pizza> listaPizzas, ArrayList<Producto> listaProductos, int total) {
        this.fPago = fPago;
        this.fecha = new Date(Calendar.getInstance().getTime().getTime());
        this.estado = estado;
        this.idOrden = idOrden;
        this.listaPizzas = listaPizzas;
        this.listaProductos = listaProductos;
        this.total = total;
    }

    public String getfPago() {
        return fPago;
    }

    @XmlElement(name = "fPago")
    public void setfPago(String fPago) {
        this.fPago = fPago;
    }

    public Date getFecha() {
        return fecha;
    }

    @XmlElement(name = "fecha")
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    @XmlElement(name = "estado")
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    @XmlElement(name = "idOrden")
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public ArrayList<Pizza> getListaPizzas() {
        return listaPizzas;
    }

    @XmlElement(name = "listaPizzas")
    public void setListaPizzas(ArrayList<Pizza> listaPizzas) {
        this.listaPizzas = listaPizzas;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public int getTotal() {
        return total;
    }

    @XmlElement(name = "total")
    public void setTotal(int total) {
        this.total = total;
    }
    
    @XmlElement(name = "listaProductos")
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Orden() {
        this.fecha = new Date(Calendar.getInstance().getTime().getTime());
        this.estado = "";
        this.idOrden = 0;
        this.listaPizzas = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.fPago = "";
        this.total = 0;
    }

    public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("idOrden", getIdOrden());
        r.put("fecha", getFecha());
        r.put("estado", getEstado());
        r.put("fPago", getfPago());
        r.put("listaPizzas", getListaPizzas());
        r.put("listaProductos", getListaProductos());
        r.put("total", getTotal());
        return r;
    }

    @Override
    public String toString() {
        return toJSON().toString(4);
    }

    private String fPago;
    private Date fecha;
    private String estado;
    private int idOrden;
    private int total;
    private ArrayList<Pizza> listaPizzas;
    private ArrayList<Producto> listaProductos;
}
