package cr.ac.ucenfotec.bl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Subasta {

    private Usuario creador;
    private double precioMinimo;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private String estado;
    private ArrayList<Oferta> oferta;

    // Constructores
    public Subasta() {
        this.creador = null;
        this.precioMinimo = 0.0;
        this.fechaCreacion = null;
        this.fechaCierre = null;
        this.estado = "";
        this.oferta = new ArrayList<>();
    }

    public Subasta(Usuario creador, double precioMinimo,
                   LocalDate fechaCreacion, LocalDate fechaCierre, String estado) {

        this.creador = creador;
        this.precioMinimo = precioMinimo;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.oferta = new ArrayList<>();
    }

    // Metodo para obtener la puntuacion del creador
    public double getPuntuacionCreador() {
        return creador.getPuntuacion();
    }

    //Metodo para agregar una oferta a la subasta
    public void agregarOferta(Oferta oferta) {
        this.oferta.add(oferta);
    }

    // Getters
    public Usuario getCreador() {
        return creador;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
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

    public ArrayList<Oferta> getOferta() {
        return new ArrayList<>(oferta);
    }

    // Setters
    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
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

    public void setOferta(ArrayList<Oferta> oferta) {
        this.oferta = new ArrayList<>(oferta);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String resultado = "\n--SUBASTA--";
        resultado += "\nNombre del creador: " + creador.getNombreCompleto();
        resultado += "\nPrecio inicial: " + precioMinimo;
        resultado += "\nFecha creacion: " + fechaCreacion.format(formatter);
        resultado += "\nFecha cierre: " + fechaCierre.format(formatter);
        resultado += "\nEstado: " + estado;

        if (oferta.isEmpty()) {
            resultado += "\nOfertas: Ninguna";
        } else {
            for (int i = 0; i < oferta.size(); i++) {
                resultado += oferta.get(i).toString();
            }
        }
        return resultado;
    }
}
