package cr.ac.ucenfotec.bl;
import java.time.LocalDate;

public class Vendedor extends Usuario{
    private double puntuacion;
    private String direccion;
    
    //constructor por defecto
    public Vendedor(){
        super();
    }

    //Constructor completo
    public Vendedor(String nombreCompleto, String identificacion, LocalDate fechaNacimiento, String contrasena, String correoElectronico, double puntuacion, String direccion){
        super(nombreCompleto, identificacion, fechaNacimiento, contrasena, correoElectronico);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
    }

    //Getters
    public double getPuntuacion(){
        return puntuacion;
    }

    public String getDireccion(){
        return direccion;
    }

    //Setters
    public void setPuntuacion(double puntuacion){
        this.puntuacion = puntuacion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    @Override
    public String getTipoUsuario(){
        return "VENDEDOR";
    }

    @Override
    public String toString(){
        return "\n--VENDEDOR--\n" + 
               super.toString() +
               "\nPuntuacion: " + puntuacion +
               "\nDireccion: " + direccion;   
    }
}