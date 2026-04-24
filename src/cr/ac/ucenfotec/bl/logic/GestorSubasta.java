package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorSubasta {

    public static String crearSubasta(Usuario creador, double precioMinimo, LocalDate fechaCreacion,
                                      LocalDate fechaCierre, String estado, ArrayList<Objetos> objetos)
            throws SQLException, IOException, ClassNotFoundException {
        if (objetos == null || objetos.isEmpty())
            return "No se puede crear una subasta sin objetos.";

        // Insertar objetos en BD y recoger sus IDs
        ArrayList<Integer> idsObjetos = new ArrayList<>();
        for (Objetos obj : objetos) {
            int idObjeto = DAOObjeto.insertarObjeto(obj, creador.getIdentificacion());
            idsObjetos.add(idObjeto);
        }

        Subasta subasta = new Subasta(creador, precioMinimo, fechaCreacion, fechaCierre, estado);
        for (Objetos obj : objetos) subasta.agregarObjeto(obj);
        return DAOSubasta.insertarSubasta(subasta, idsObjetos);
    }

    public static ArrayList<Subasta> obtenerSubastas(ArrayList<Usuario> usuarios)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.seleccionarTodas(usuarios);
    }

    public static String actualizarEstado(int idSubasta, String nuevoEstado)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.actualizarEstado(idSubasta, nuevoEstado);
    }
}
