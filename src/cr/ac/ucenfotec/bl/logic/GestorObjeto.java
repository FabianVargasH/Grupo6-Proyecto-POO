package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.entities.Objetos;

import java.io.IOException;
import java.sql.SQLException;

public class GestorObjeto {

    public static String registrarObjeto(Objetos objeto, String propietarioId) throws SQLException, IOException, ClassNotFoundException, SQLException, IOException {
        return DAOObjeto.insertarObjeto(objeto, propietarioId);
    }

    public static void listarObjetosPorPropietario(String propietarioId) throws SQLException, IOException, ClassNotFoundException {
        DAOObjeto.leerObjetosPorPropietario(propietarioId);
    }
    public static void listarObjetosEnPlataforma()
            throws SQLException, IOException, ClassNotFoundException {
        DAOObjeto.listarObjetosEnPlataforma();
    }
}