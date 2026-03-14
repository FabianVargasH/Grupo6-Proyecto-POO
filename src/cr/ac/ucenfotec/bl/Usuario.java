package cr.ac.ucenfotec.bl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Usuario{
    private String nombreCompleto;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private String contrasena;
    private String correoElectronico;

    //Constructor por defecto
    public Usuario(){}

    //Constructor completo
    public Usuario(String nombreCompleto, String identificacion, LocalDate fechaNacimiento, String contrasena, String correoElectronica){
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronica;
    }

    //Metodo para calcular la edad de cada usuario
    public int calcularEdad(){
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear()-fechaNacimiento.getYear();
        int mesHoy = hoy.getMonthValue();
        int mesNac = fechaNacimiento.getMonthValue();
        if(mesHoy<mesNac){
            edad--;
        }else if(mesHoy == mesNac && hoy.getDayOfMonth()<fechaNacimiento.getDayOfMonth()){
            edad--;
        }
        return edad;
    }

    //Metodo abstracto que deben implementar las clases hijas (Los diferentes tipos de usuarios)
    public abstract String getTipoUsuario();

    //Metodo para la puntuacion del vendedor y el coleccionista
    public double getPuntuacion() {
        return 0;
    }

    //Getters
    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public String getIdentificacion(){
        return identificacion;
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public String getContrasena(){
        return contrasena;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    //Setters
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto = nombreCompleto;
    }

    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\nNombre: " + nombreCompleto +
               "\nIdentificación: " + identificacion +
               "\nFecha Nacimiento: " + fechaNacimiento.format(formatter) + 
               "\nEdad: " + calcularEdad() + " años" +
               "\nCorreo electronico: " + correoElectronico;
    }
}