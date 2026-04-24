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
            if (oferta.getPrecioOfertado() > mejorOferta.getPrecioOfertado()) mejorOferta = oferta;
        }

        // Buscar el ID del ganador
        String ganadorId = buscarIdPorNombre(usuarios, mejorOferta.getNombreOferente());
        if (ganadorId == null) return "No se encontró el usuario ganador.";

        // Buscar IDs de objetos en BD
        ArrayList<Integer> idsObjetos = buscarIdsObjetos(idSubasta);

        // Crear orden
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

    private static String buscarIdPorNombre(ArrayList<Usuario> usuarios, String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombreCompleto().equals(nombre)) return u.getIdentificacion();
        }
        return null;
    }

    private static ArrayList<Integer> buscarIdsObjetos(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Objetos> objetos = DAOObjeto.seleccionarObjetosPorSubasta(idSubasta);
        // Obtener IDs reales de la BD via consulta directa
        cr.ac.ucenfotec.dl.Conector.getConexion();
        String query = "SELECT objeto_id FROM t_subasta_objeto WHERE subasta_id = ?;";
        java.sql.ResultSet rs = cr.ac.ucenfotec.dl.Conector.getConexion()
                .ejecutarQuery(query, String.valueOf(idSubasta));
        while (rs.next()) ids.add(rs.getInt("objeto_id"));
        return ids;
    }
}
