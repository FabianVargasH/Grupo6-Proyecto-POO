package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOVendedor;
import cr.ac.ucenfotec.bl.entities.Vendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorVendedor {

    public static String registrarVendedor(String nombreCompleto, String identificacion, LocalDate fechaNacimiento, String contrasena, String correoElectronico, double puntuacion, String direccion) throws SQLException, IOException, ClassNotFoundException {
        Vendedor vendedor = new Vendedor(nombreCompleto, identificacion, fechaNacimiento, contrasena, correoElectronico, puntuacion, direccion);
        if (vendedor.calcularEdad() < 18) {
            return "El vendedor debe ser mayor de 18 años.";
        }
        if (DAOVendedor.existeVendedor(identificacion)) {
            return "Ya existe un usuario con esa identificación.";
        }
        return DAOVendedor.insertarVendedor(vendedor);
    }

    public static Vendedor buscarVendedor(String identificacion) throws SQLException, IOException, ClassNotFoundException {
        return DAOVendedor.seleccionarVendedor(identificacion);
    }

    public static void listarVendedores() throws SQLException, IOException, ClassNotFoundException {
        DAOVendedor.leerVendedores();
    }
}
