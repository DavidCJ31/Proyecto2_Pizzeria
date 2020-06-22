package clases;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import org.json.JSONObject;

public class Orden {

    public Orden(String fPago, String estado, int idOrden, ArrayList<Pizza> listaPizzas, ArrayList<Producto> listaProductos) {
        this.fPago = fPago;
        this.fecha = new Date();
        this.estado = estado;
        this.idOrden = idOrden;
        this.listaPizzas = listaPizzas;
        this.listaProductos = listaProductos;
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

    @XmlElement(name = "listaProductos")
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Orden() {
        this.fecha = new Date();
        this.estado = "";
        this.idOrden = 0;
        this.listaPizzas = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.fPago = "";
    }

    public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("idOrden", getIdOrden());
        r.put("fecha", getFecha());
        r.put("estado", getEstado());
        r.put("fPago", getfPago());
        r.put("listaPizzas", getListaPizzas());
        r.put("listaProductos", getListaProductos());
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
    private ArrayList<Pizza> listaPizzas;
    private ArrayList<Producto> listaProductos;
}
