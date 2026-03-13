package cr.ac.ucenfotec.bl;


public class Oferta {

    private String nombreOferente;
    private double puntuacionOferente;
    private double precioOfertado;

    //Constructor por defecto
    public Oferta(){
    }

    //Constructor completo
    public Oferta(String nombreOferente, double puntuacionOferente, double precioOfertado){
        this.nombreOferente = nombreOferente;
        this.puntuacionOferente = puntuacionOferente;
        this.precioOfertado = precioOfertado;
    }

    //Getters
    public String getNombreOferente(){
        return nombreOferente;
    }

    public double getPuntuacionOferente(){
        return puntuacionOferente;
    }

    public double getPrecioOfertado(){
        return precioOfertado;
    }

    //Setters
    public void setNombreOferente(String nombreOferente){
        this.nombreOferente = nombreOferente;
    }

    public void setPuntuacionOferente(double puntuacionOferente){
        this.puntuacionOferente = puntuacionOferente;
    }

    public void setPrecioOfertado(double precioOfertado){
        this.precioOfertado = precioOfertado;
    }

    @Override
    public String toString(){
        return "\n--OFERTA--" +
                "\nOferente: " + nombreOferente +
                "\nPuntuacion del oferente: " + puntuacionOferente +
                "\nPrecio ofertado: " + precioOfertado;
    }
}