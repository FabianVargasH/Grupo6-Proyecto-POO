package cr.ac.ucenfotec.bl;
import java.time.LocalDate;
import java.util.ArrayList;

public class Coleccionista extends Usuario{
    private double puntuacion;
    private String direccion;
    private ArrayList<String> intereses;
    private ArrayList<String> objetosPropiedad; //Para este primer avance sera un String, despues sera ArrayList<Objeto>

    //Constructor por defecto
    public Coleccionista(){
        super();
        this.intereses = new ArrayList<>();
        this.objetosPropiedad = new ArrayList<>();
    }

    //Constructor completo
    public Coleccionista(String nombreCompleto, String identificacion, LocalDate fechaNacimiento, String contrasena, String correoElectronico, double puntuacion, String direccion){
        super(nombreCompleto, identificacion, fechaNacimiento, contrasena, correoElectronico);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
        this.intereses = new ArrayList<>();
        this.objetosPropiedad = new ArrayList<>();
    }

    //Metodos para manejar los intereses
    public void agregarInteres(String interes){
        this.intereses.add(interes);
    }

    public boolean eliminarInteres(String interes){
        return this.intereses.remove(interes);
    }

    //Metodo para manejar objetos
    public void agregarObjetoPropiedad(String objeto){
        this.objetosPropiedad.add(objeto);
    }

    public boolean eliminarObjetoPropiedad(String objeto){
        return this.objetosPropiedad.remove(objeto);
    }

    //Getters
    public double getPuntuacion(){
        return puntuacion;
    }

    public String getDireccion(){
        return direccion;
    }

    public ArrayList<String> getIntereses(){
        return new ArrayList<>(intereses);
    }

    public ArrayList<String> getObjetosPropiedad(){
        return new ArrayList<>(objetosPropiedad);
    }

    @Override
    public String getTipoUsuario(){
        return "COLECCIONISTA";
    }

    //Setters
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setPuntuacion(double puntuacion){
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString(){
        String resultado ="\n--COLECCIONISTA--\n";
        resultado += super.toString();
        resultado += "\nPuntuación: " + puntuacion;
        resultado += "\nDirección: " + direccion;
        resultado += "\nIntereses: ";

        if (intereses.isEmpty()) {
            resultado += "Ninguno";
        } else {
            for (String interes : intereses) {
                resultado += "\n  - " + interes;
            }
        }
        resultado += "\nObjetos en propiedad: ";
        if (objetosPropiedad.isEmpty()) {
            resultado += "Ninguno";
        } else {
            for (String objeto : objetosPropiedad) {
                resultado += "\n  - " + objeto;
            }
        }
        return resultado;
    }
}