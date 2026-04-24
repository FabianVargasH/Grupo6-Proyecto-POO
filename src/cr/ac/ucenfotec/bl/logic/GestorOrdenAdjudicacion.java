package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.dao.DAOOrdenAdjudicacion;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorOrdenAdjudicacion {

    public static String adjudicarSubasta(int idSubasta, Subasta subasta, ArrayList<Usuario> usuarios)
            throws SQLException, IOException, ClassNotFoundException {
        if (subasta.getEstado().startsWith("Adjudicada"))
            return "Esta subasta ya fue adjudicada.";

        ArrayList<Oferta> ofertas = subasta.getOferta();
        if (ofertas.isEmpty())
            return "No hay ofertas en esta subasta. No se puede adjudicar.";

        // Determinar mejor oferta
        Oferta mejorOferta = ofertas.get(0);
        for (Oferta oferta : ofertas) {
            if (oferta.getPrecioOfertado() > mejorOferta.getPrecioOfertado())
                mejorOferta = oferta;
        }

        // Buscar ID del ganador en la lista de usuarios
        String ganadorId = null;
        for (Usuario u : usuarios) {
            if (u.getNombreCompleto().equals(mejorOferta.getNombreOferente())) {
                ganadorId = u.getIdentificacion();
                break;
            }
        }
        if (ganadorId == null) return "No se encontró el usuario ganador.";

        // Obtener IDs de los objetos de la subasta
        ArrayList<Integer> idsObjetos = DAOObjeto.seleccionarIdsPorSubasta(idSubasta);

        // Crear y persistir la orden
        OrdenAdjudicacion orden = new OrdenAdjudicacion(
                mejorOferta.getNombreOferente(), LocalDate.now(), mejorOferta.getPrecioOfertado());
        for (Objetos obj : subasta.getObjetos()) orden.agregarObjetoAdjudicado(obj);

        DAOOrdenAdjudicacion.insertarOrden(orden, idSubasta, ganadorId, idsObjetos);
        DAOSubasta.actualizarEstado(idSubasta, "Adjudicada - Ganador: " + mejorOferta.getNombreOferente());

        return orden.toString();
    }

    public static void listarOrdenes() throws SQLException, IOException, ClassNotFoundException {
        DAOOrdenAdjudicacion.leerOrdenes();
    }
}
