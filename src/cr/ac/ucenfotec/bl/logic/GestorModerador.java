package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOModerador;
import cr.ac.ucenfotec.bl.entities.Moderador;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorModerador {

    public static String registrarModerador(String nombreCompleto, String identificacion,
                                            LocalDate fechaNacimiento, String contrasena,
                                            String correoElectronico)
            throws SQLException, IOException, ClassNotFoundException {
        Moderador moderador = new Moderador(nombreCompleto, identificacion,
                fechaNacimiento, contrasena, correoElectronico);
        if (moderador.calcularEdad() < 18) return "El moderador debe ser mayor de 18 años.";
        return DAOModerador.insertarModerador(moderador);
    }

    public static boolean existeModerador() throws SQLException, IOException, ClassNotFoundException {
        return DAOModerador.existeModerador();
    }

    public static void listarModerador() throws SQLException, IOException, ClassNotFoundException {
        DAOModerador.leerModerador();
    }
}
