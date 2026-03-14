package cr.ac.ucenfotec.bl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Subasta {

    private String nombreArticulo;
    private String descripcion;
    private double precioInicial;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private String estado;
    private Oferta oferta;

    // Constructores
    public Subasta(){
        this.nombreArticulo = "";
        this.descripcion = "";
        this.precioInicial = 0.0;
        this.fechaCreacion = null;
        this.fechaCierre = null;
        this.estado = "";
        this.oferta = null;
    }

    public Subasta(String nombreArticulo, String descripcion, double precioInicial,
                   LocalDate fechaCreacion, LocalDate fechaCierre, String estado){

        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
        this.precioInicial = precioInicial;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
    }

    // Getters
    public String getNombreArticulo(){
        return nombreArticulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public double getPrecioInicial(){
        return precioInicial;
    }

    public LocalDate getFechaCreacion(){
        return fechaCreacion;
    }

    public LocalDate getFechaCierre(){
        return fechaCierre;
    }

    public String getEstado(){
        return estado;
    }

    public Oferta getOferta(){
        return oferta;
    }

    // Setters
    public void setNombreArticulo(String nombreArticulo){
        this.nombreArticulo = nombreArticulo;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setPrecioInicial(double precioInicial){
        this.precioInicial = precioInicial;
    }

    public void setFechaCreacion(LocalDate fechaCreacion){
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaCierre(LocalDate fechaCierre){
        this.fechaCierre = fechaCierre;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setOferta(Oferta oferta){
        this.oferta = oferta;
    }

    @Override
    public String toString(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String resultado = "\n--SUBASTA--";
        resultado += "\nNombre del articulo: " + nombreArticulo;
        resultado += "\nDescripcion: " + descripcion;
        resultado += "\nPrecio inicial: " + precioInicial;
        resultado += "\nFecha creacion: " + fechaCreacion.format(formatter);
        resultado += "\nFecha cierre: " + fechaCierre.format(formatter);
        resultado += "\nEstado: " + estado;

        if(oferta != null){
            resultado += oferta.toString();
        }else{
            resultado += "\nOferta actual: Ninguna";
        }

        return resultado;
    }
}
