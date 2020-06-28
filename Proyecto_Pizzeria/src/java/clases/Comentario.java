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
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import org.json.JSONObject;

/**
 *
 * @author mario
 */
public class Comentario {

    public Comentario(String idUsuario, String descripcion) {
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.fecha = new Date(Calendar.getInstance().getTime().getTime());
    }

    public Comentario() {
        this.idUsuario = "";
        this.descripcion = "";
        this.fecha = new Date(Calendar.getInstance().getTime().getTime());
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    @XmlElement(name = "idUsuario")
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @XmlElement(name = "descripcion")
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    @XmlElement(name = "fecha")
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("idUsuario", getIdUsuario());
        r.put("descripcion", getDescripcion());
        r.put("fecha", getFecha());
        return r;
    }

    @Override
    public String toString() {
        return toJSON().toString(4);
    }

    private String idUsuario;
    private String descripcion;
    private Date fecha;
}
