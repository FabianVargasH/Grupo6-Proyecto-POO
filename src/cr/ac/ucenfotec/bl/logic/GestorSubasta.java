package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorSubasta {

    public static String crearSubasta(Usuario creador, double precioMinimo,
                                      LocalDate fechaCreacion, LocalDate fechaCierre,
                                      String estado, ArrayList<Objetos> objetos)
            throws SQLException, IOException, ClassNotFoundException {
        if (objetos == null || objetos.isEmpty()) {
            return "No se puede crear una subasta sin objetos asociados.";
        }
        int idSubasta = DAOSubasta.insertarSubastaYObtenerId(
                creador.getIdentificacion().trim(),
                precioMinimo,
                fechaCreacion,
                fechaCierre,
                estado
        );
        if (idSubasta == -1) {
            return "Error al crear la subasta.";
        }
        for (Objetos obj : objetos) {
            int idObjeto = DAOObjeto.insertarObjetoYObtenerId(obj, creador.getIdentificacion().trim());
            if (idObjeto != -1) {
                DAOSubasta.relacionarObjetoSubasta(idSubasta, idObjeto);
            }
        }
        return "Subasta creada exitosamente con " + objetos.size() + " objetos. ID Subasta: " + idSubasta;
    }

    public static void listarSubastas() throws SQLException, IOException, ClassNotFoundException {
        DAOSubasta.leerSubastas();
    }
}
