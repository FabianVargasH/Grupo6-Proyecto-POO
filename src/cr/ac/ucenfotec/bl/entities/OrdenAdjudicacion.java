package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrdenAdjudicacion {
    private String nombreGanador;
    private LocalDate fechaOrden;
    private ArrayList<Objetos> objetosAdjudicados;
    private double precioTotal;

    // Constructor vacio
    public OrdenAdjudicacion() {
        this.nombreGanador = "";
        this.fechaOrden = null;
        this.objetosAdjudicados = new ArrayList<>();
        this.precioTotal = 0.0;
    }

    //Constructor completo
    public OrdenAdjudicacion(String nombreGanador, LocalDate fechaOrden, double precioTotal) {
        this.nombreGanador = nombreGanador;
        this.fechaOrden = fechaOrden;
        this.objetosAdjudicados = new ArrayList<>();
        this.precioTotal = precioTotal;
    }

    // Métodos para manejar objetos adjudicados
    public void agregarObjetoAdjudicado(Objetos objeto) {
        this.objetosAdjudicados.add(objeto);
    }

    public boolean eliminarObjetoAdjudicado(Objetos objeto) {
        return this.objetosAdjudicados.remove(objeto);
    }

    // Getters
    public String getNombreGanador() {
        return nombreGanador;
    }

    public LocalDate getFechaOrden() {
        return fechaOrden;
    }

    public ArrayList<Objetos> getObjetosAdjudicados() {
        return new ArrayList<>(objetosAdjudicados);
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    // Setters
    public void setNombreGanador(String nombreGanador) {
        this.nombreGanador = nombreGanador;
    }

    public void setFechaOrden(LocalDate fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = "\n--ORDEN DE ADJUDICACIÓN--";
        resultado += "\nNombre del ganador: " + nombreGanador;
        resultado += "\nFecha de la orden: " + fechaOrden.format(formatter);
        resultado += "\nPrecio total: " + precioTotal;

        if (objetosAdjudicados.isEmpty()) {
            resultado += "\nObjetos adjudicados: Ninguno";
        } else {
            resultado += "\nObjetos adjudicados:";
            for (Objetos objeto : objetosAdjudicados) {
                resultado += "\n  - " + objeto.getNombre() + " (" + objeto.getEstado() + ")";
            }
        }
        return resultado;
    }
}