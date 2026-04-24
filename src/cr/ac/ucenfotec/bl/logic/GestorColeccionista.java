package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOColeccionista;
import cr.ac.ucenfotec.bl.entities.Coleccionista;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorColeccionista {

    public static String registrarColeccionista(String nombreCompleto, String identificacion,
                                                LocalDate fechaNacimiento, String contrasena,
                                                String correoElectronico, double puntuacion,
                                                String direccion) throws SQLException, IOException, ClassNotFoundException {
        Coleccionista coleccionista = new Coleccionista(nombreCompleto, identificacion, fechaNacimiento,
                contrasena, correoElectronico, puntuacion, direccion);
        if (coleccionista.calcularEdad() < 18) return "El coleccionista debe ser mayor de 18 años.";
        return DAOColeccionista.insertarColeccionista(coleccionista);
    }

    public static String agregarInteres(String identificacion, String interes)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOColeccionista.insertarInteres(identificacion, interes);
    }

    public static ArrayList<Coleccionista> obtenerColeccionistas()
            throws SQLException, IOException, ClassNotFoundException {
        return DAOColeccionista.seleccionarTodos();
    }

    public static void listarColeccionistas() throws SQLException, IOException, ClassNotFoundException {
        DAOColeccionista.leerColeccionistas();
    }
}
