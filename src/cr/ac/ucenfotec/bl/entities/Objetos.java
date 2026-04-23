package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Objetos {
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate fechaCompra;

    // Constructor vacio
    public Objetos() {
        this.nombre = "";
        this.descripcion = "";
        this.estado = "";
        this.fechaCompra = null;
    }
    // Constructor completo
    public Objetos(String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    // Metodo para calcular la antigüedad en años, meses y días
    public String calcularAntigedad() {
        LocalDate hoy = LocalDate.now();

        int anios = hoy.getYear() - fechaCompra.getYear();
        int meses = hoy.getMonthValue() - fechaCompra.getMonthValue();
        int dias = hoy.getDayOfMonth() - fechaCompra.getDayOfMonth();
        if (dias < 0) {
            meses--;
            LocalDate mesAnterior = hoy.minusMonths(1);
            dias += mesAnterior.lengthOfMonth();
        }
        if (meses < 0) {
            anios--;
            meses += 12;
        }
        return anios + " año(s), " + meses + " mes(es) y " + dias + " día(s)";
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\n--Objeto--" +
                "\nNombre: " + nombre +
                "\nDescripción: " + descripcion +
                "\nEstado: " + estado +
                "\nFecha de compra: " + fechaCompra.format(formatter) +
                "\nAntigüedad: " + calcularAntigedad();
    }
}