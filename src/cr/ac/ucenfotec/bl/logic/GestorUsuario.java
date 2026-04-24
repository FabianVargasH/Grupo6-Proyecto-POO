package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorUsuario {

    public static String registrarVendedor(String nombreCompleto, String identificacion, LocalDate fechaNacimiento,
                                           String contrasena, String correoElectronico, double puntuacion,
                                           String direccion) throws SQLException, IOException, ClassNotFoundException {
        Vendedor vendedor = new Vendedor(nombreCompleto, identificacion, fechaNacimiento,
                contrasena, correoElectronico, puntuacion, direccion);
        if (vendedor.calcularEdad() < 18) return "El vendedor debe ser mayor de 18 años.";
        return DAOUsuario.insertarUsuario(vendedor);
    }

    public static String registrarColeccionista(String nombreCompleto, String identificacion, LocalDate fechaNacimiento,
                                                String contrasena, String correoElectronico, double puntuacion,
                                                String direccion) throws SQLException, IOException, ClassNotFoundException {
        Coleccionista coleccionista = new Coleccionista(nombreCompleto, identificacion, fechaNacimiento,
                contrasena, correoElectronico, puntuacion, direccion);
        if (coleccionista.calcularEdad() < 18) return "El coleccionista debe ser mayor de 18 años.";
        return DAOUsuario.insertarUsuario(coleccionista);
    }

    public static String registrarModerador(String nombreCompleto, String identificacion, LocalDate fechaNacimiento,
                                            String contrasena, String correoElectronico)
            throws SQLException, IOException, ClassNotFoundException {
        Moderador moderador = new Moderador(nombreCompleto, identificacion, fechaNacimiento, contrasena, correoElectronico);
        if (moderador.calcularEdad() < 18) return "El moderador debe ser mayor de 18 años.";
        return DAOUsuario.insertarUsuario(moderador);
    }

    public static String agregarInteres(String identificacionColeccionista, String interes)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuario.insertarInteres(identificacionColeccionista, interes);
    }

    public static boolean existeModerador() throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuario.existeModerador();
    }

    public static ArrayList<Usuario> obtenerTodosUsuarios() throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuario.seleccionarTodos();
    }

    public static void listarUsuarios() throws SQLException, IOException, ClassNotFoundException {
        DAOUsuario.leerUsuarios();
    }
}
