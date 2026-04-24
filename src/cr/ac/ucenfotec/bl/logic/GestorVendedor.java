package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOVendedor;
import cr.ac.ucenfotec.bl.entities.Vendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorVendedor {

    public static String registrarVendedor(String nombreCompleto, String identificacion,
                                           LocalDate fechaNacimiento, String contrasena,
                                           String correoElectronico, double puntuacion,
                                           String direccion) throws SQLException, IOException, ClassNotFoundException {
        Vendedor vendedor = new Vendedor(nombreCompleto, identificacion, fechaNacimiento,
                contrasena, correoElectronico, puntuacion, direccion);
        if (vendedor.calcularEdad() < 18) return "El vendedor debe ser mayor de 18 años.";
        return DAOVendedor.insertarVendedor(vendedor);
    }

    public static ArrayList<Vendedor> obtenerVendedores()
            throws SQLException, IOException, ClassNotFoundException {
        return DAOVendedor.seleccionarTodos();
    }

    public static void listarVendedores() throws SQLException, IOException, ClassNotFoundException {
        DAOVendedor.leerVendedores();
    }
}
