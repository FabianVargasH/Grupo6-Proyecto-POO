package cr.ac.ucenfotec.bl;
import java.time.LocalDate;
public class Moderador extends Usuario{

    //Constructor por defecto
    public Moderador(){
        super();
    }

    //Constructor completo
    public Moderador(String nombreCompleto, String identificacion, LocalDate fechaNacimiento, String contrasena, String correoElectronico){
        super(nombreCompleto, identificacion, fechaNacimiento, contrasena, correoElectronico);
    }

    @Override
    public String getTipoUsuario(){
        return "MODERADOR";
    }

    @Override
    public String toString(){
        return "\n--MODERADOR--\n" + super.toString();
    }
}