package cr.ac.ucenfotec.bl;

import java.time.LocalDate;

public class Subasta {

    private String nombreArticulo;
    private String descripcion;
    private double precioInicial;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private String estado;
    private Oferta oferta;

    // Constructor por defecto
    public Subasta() {
        this.nombreArticulo = "";
        this.descripcion = "";
        this.precioInicial = 0.0;
        this.fechaCreacion = null;
        this.fechaCierre = null;
        this.estado = "";
        this.oferta = null;
    }

    // Constructor completo
    public Subasta(String nombreArticulo, String descripcion, double precioInicial,
                   LocalDate fechaCreacion, LocalDate fechaCierre, String estado, Oferta oferta) {
        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
        this.precioInicial = precioInicial;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.oferta = oferta;
    }

    // Getters
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public String getEstado() {
        return estado;
    }

    public Oferta getOferta() {
        return oferta;
    }

    // Setters
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioInicial(double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        String resultado = "\n SUBASTA " +
                "\nNombre del articulo: " + nombreArticulo +
                "\nDescripcion: " + descripcion +
                "\nPrecio inicial: " + precioInicial +
                "\nFecha de creacion: " + fechaCreacion +
                "\nFecha de cierre: " + fechaCierre +
                "\nEstado: " + estado;

        if (oferta != null) {
            resultado += "\nOferta registrada: " + oferta.toString();
        } else {
            resultado += "\nOferta registrada: Ninguna";
        }

        return resultado;
    }
}
